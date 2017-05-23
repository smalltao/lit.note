# `mysql` 中关于锁的操作
> 不同的存储引擎支持不同的锁机制

1. `MyISAM`和`MEMORY`存储引擎采用的是表级锁（table-level locking）
2. `BDB`存储引擎采用的是页面锁（page-level locking）, 但是也支持表级锁
3. `InnoDB`存储引擎采用的是行级锁（row-level locking）,也支持表级锁，但是默认情况下采用行级锁

> `mysql` 三种锁特性

名称 | 开销 | 加锁速度 | 死锁 | 粒度 | 并发性能
--- | --- | --- | --- | --- | ---
表级锁 | 开销小 | 加锁快 | 不会出现死锁 | 锁定粒度大 | 发生锁冲突的概率最高，并发度最低
行级锁 | 开销大 | 加锁慢 | 会出现死锁 | 锁定粒度最小 | 发生锁冲突的概率最低，并发度也最高
页面锁 | 开销和加锁时间介于表锁和行锁之间| 同左 |会出现死锁|锁定粒度介于表锁和行锁之间 | 并发度一般

1. 表级锁适合以查询为主，只有少量按索引条件更新数据的应用，如web应用
2. 行级锁更适合大量按索引条件并发更新少量不同数据，同时又有并发查询的应用，如在事物处理系统（OLTP）

## `MyISAM` 表锁
### 查询表级锁争用情况

> 通过检查 `table_locks_waited` 和 `table_locks_immediate` 状态变量来分析系统上的表锁定争议

```
mysql> show status like 'table%';
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| Table_locks_immediate | 2979  |
| Table_locks_waited    | 0     |
+-----------------------+-------+
2 rows in set (0.00 sec))
如果Table_locks_waited的值比较高，则说明存在着较严重的表级锁争用情况。
```

### `MySQL` 表级锁的锁模式

> `MySQL` 的表级锁有两种模式：表共享读锁（Table Read Lock） 和 表独占写锁（Table Write Lock）

1. 对`MyISAM`表的读操作，不会阻塞其他用户对同一表的读请求，但是会阻塞对同一表的写请求
2. 对`MyISAM`表的写操作，会阻塞其他用户对同一表的读和写操作
3. `MyISAM`表的读操作与写操作之间，以及写操作之间是串行的

> 当一个线程获得对一个表的写锁后，只有持有锁的线程可以对表进行更新操作。其他线程的读、写操作都会等待，直到锁被释放为止。

session_01 | session_02
--- | ---
获取表film_text的WRITE锁 <br />mysql> lock table film_text write;<br /> Query OK, 0 rows affected (0.00 sec)|
当前session对锁定表的查询、更新、插入操作都可以执行：<br />mysql> select film_id,title from film_text where film_id = 1001; | 其他 session 对锁定表的查询被阻塞，需等待锁被释放
释放锁：unlock tables; | 等待
  | session2 获得锁，返回查询结果

### 如何加表锁

> `MyISAM` 在执行查询语句（SELECT）前，会自动给涉及的所有表加读锁，在执行更新操作（UPDATE、DELETE、INSERT等）前,会自动给涉及的表加写锁，这个过程不需要用户干预，所有不用显示执行 LOCK TABLE命令显式加锁
> 给`MyISAM表`显示加锁，一般是为了在一定程度模拟事物操作，实现对某一时间点多个表的一致性读取。

1. 例如，有一个订单表orders, 其中记录有各订单的总金额total，同时还有一个订单明细表order_detail,其中记录有各订单每一个产品的金额小计 subtotal，假设我们需要检查这两个表的金额合计是否相等，可能需要执行如下两条sql：
```
select sum(total) from orders;
select sum(subtotal) from order_detail;
Unlock tables;
```
> 这时，如果不先给这两个表加锁 ，就可能产生错误的结果，因为第一条结果执行过程中，roder_detail表可能已经发生了改变，因此，正确的方法应该是：
```
lock tables orders read local, order_detail read local;
select sum(total) from orders;
select sum(subtotal) from order_detail;
```

>注意以下两点内容

1. 上面的例子在lock tables时加了“local” 选项，其作用就是在满足MyISAM表并发插入条件的情况下，允许其他用户在表末尾并发插入记录
2. 在用lock tables给表显示加表锁时，必须同时取得所有涉及到的表的锁，并且MySQL不支持锁升级，也就是说，在执行lock tables后，只能访问显式加锁的这些表，不能访问未加锁的表，同时，如果加的是读锁，那么只能执行查询操作，而不能执行更新操作。

> 在自动加锁的情况下也如第二条，`MyISAM` 总是一次获得sql语句所需要的全部锁，这也正是MyISAM 表不会出现死锁的原因

> 如下例子： 一个session使用lock table命令给表film_text加了读锁，这个session可以查询锁定表中的记录，但更新或访问其他表都会提示错误，同时，另一个session可以查询表中的记录，但是更新就会出现锁等待

session_01 | session_02
--- | ---
获的表file_text的READ锁定<br /> mysql> lock table file_text read; |
当前session可以查询该表记录<br /> smyslq> elect film_id,title from film_text where film_id=1000 | 其他session也可以查询该表记录<br /> mysql> select film_id,title from film_text where film_id=1001;
当前session不能查询没有锁定的表<br />select film_id,title from film_text where film_id=1000<br /> ERROR 1100(HY000): Table 'film' was not locked with LOCK TABLES | 其他session可以查询或更新未锁定的表
当前session中插入或者更新锁定的表都会提示错误 | 其他session更新锁定的表会等待获得锁
释放锁 <br /> mysql> unlock tables;| 等待
   | session获得锁，更新操作完成

> 当使用lock tables 时，不仅需要一次锁定用到的所有表，而且，同一个表在sql语句中出现多少次，就要通过与sql语句中相同的别名锁定多少次，否则也会出错
```
1. 对 actor表获得读锁：
mysql> lock table actor read;
2. 但是通过别名访问会提示错误
mysql> select a.first_name,a.last_name,b.first_name,b.last_name from actor a,actor b where a.first_name = b.first_name and a.first_name = 'Lisa' and a.last_name = 'Tom' and a.last_name <> b.last_name;
ERROR 1100 (HY000): Table 'a' was not locked with LOCK TABLES
3. 需要对别名分别锁定：
mysql> lock table actor as a read , actor as b read;
4. 按照别名的查询可以正确执行
mysql> select a.first_name,a.last_name,b.first_name,b.last_name from actor a,actor b where a.first_name = b.first_name and a.first_name = 'Lisa' and a.last_name = 'Tom' and a.last_name <> b.last_name;
```
### 并发插入
> 上文提到过 `MyISAM`表的读和写是串行的，但这是就总体而言的，在一定条件下，`MyISAM`表也支持查询和插入操作的并发进行。
> MyISAM 存储引擎有一个系统变量concurrent_insert,专门用以控制其并发插入的行为，其值分别可以为0、1 或者 2.

1. 当concurrent_insert设置为0时，不允许并发插入
2. 当concurrent_isnert设置为0时，如果MyISAM表中没有空洞（即表的中间没有被删除的行），MyISAM允许在一个进程读表的同时，另一个进程从表的末尾插入记录。这也是MySQL的默认设置
3. 当concurrent_insert设置为2时，无论MyISAM表中有没有空洞，都允许在表的末尾并发插入记录。

> 如下例子： sessin_1获得了一个表的READ LOCAL锁，该线程可以对表进行查询操作，但是不能对表进行更新操作；其他线程 sessin_2 虽然不能对表进行删除和更新操作，但是却可以对表进行并发插入操作，这里假设表中间不存在空洞。












