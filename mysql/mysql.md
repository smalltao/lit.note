# mysql 笔记

## 自增长数据的导入导出

## 忘记root密码处理
### windows
1. 首先检查mysql服务是否启动，若已启动则先将其停止服务，可在开始菜单的运行，使用命令：net stop mysql。也可以手动停止。
2. 登录windows服务器，打开第一个cmd窗口，切换到mysql的bin目录，然后运行命令：
    mysqld --defaults-file="C:\Program Files\MySQL\MySQL Server 5.1\my.ini" --console --skip-grant-tables
3. 打开第二个cmd窗口，连接mysql之后：
   输入命令：
   mysql -uroot -p
   出现：
   Enter password:
   在这里直接回车，不用输入密码。
   然后就就会出现登录成功的信息。

4. 刷新权限：
    输入：FLUSH PRIVILEGES;
    然后退出，重新登录：
    输入：quit
    重新登录：
    输入：mysql -uroot –p
    出现输入密码提示，输入新的密码即可登录：
    输入：Enter password: *********** 中间可能会有报错，整体不影响就ok

## 授权
### 用户管理
mysql>use mysql;
查看
mysql> select host,user,password from user ;
创建
mysql> create user  zx_root   IDENTIFIED by 'xxxxx';   //identified by 会将纯文本密码加密作为散列值存储
修改
mysql>rename   user  feng  to   newuser；//mysql 5之后可以使用，之前需要使用update 更新user表
删除
mysql>drop user newuser;   //mysql5之前删除用户时必须先使用revoke 删除用户权限，然后删除用户，mysql5之后drop 命令可以删除用户的同时删除用户的相关权限
更改密码
mysql> set password for zx_root =password('xxxxxx');
mysql> update  mysql.user  set  password=password('xxxx')  where user='otheruser'
查看用户权限
mysql> show grants for zx_root;
赋予权限
mysql> grant select on dmc_db.*  to zx_root;
回收权限
mysql> revoke  select on dmc_db.*  from  zx_root;  //如果权限不存在会报错
上面的命令也可使用多个权限同时赋予和回收，权限之间使用逗号分隔
mysql> grant select，update，delete  ，insert  on dmc_db.*  to  zx_root;
如果想立即看到结果使用
flush  privileges ;
命令更新

设置权限时必须给出一下信息
1，要授予的权限
2，被授予访问权限的数据库或表
3，用户名
grant和revoke可以在几个层次上控制访问权限
1，整个服务器，使用 grant ALL  和revoke  ALL
2，整个数据库，使用on  database.*
3，特点表，使用on  database.table
4，特定的列
5，特定的存储过程

user表中host列的值的意义
%              匹配所有主机
localhost    localhost不会被解析成IP地址，直接通过UNIXsocket连接
127.0.0.1      会通过TCP/IP协议连接，并且只能在本机访问；
::1                 ::1就是兼容支持ipv6的，表示同ipv4的127.0.0.1

grant 普通数据用户，查询、插入、更新、删除 数据库中所有表数据的权利。

grant select on testdb.* to common_user@’%’

grant insert on testdb.* to common_user@’%’

grant update on testdb.* to common_user@’%’

grant delete on testdb.* to common_user@’%’

或者，用一条 MySQL 命令来替代：

grant select, insert, update, delete on testdb.* to common_user@’%’

9>.grant 数据库开发人员，创建表、索引、视图、存储过程、函数。。。等权限。

grant 创建、修改、删除 MySQL 数据表结构权限。

grant create on testdb.* to developer@’192.168.0.%’;

grant alter on testdb.* to developer@’192.168.0.%’;

grant drop on testdb.* to developer@’192.168.0.%’;

grant 操作 MySQL 外键权限。

grant references on testdb.* to developer@’192.168.0.%’;

grant 操作 MySQL 临时表权限。

grant create temporary tables on testdb.* to developer@’192.168.0.%’;

grant 操作 MySQL 索引权限。

grant index on testdb.* to developer@’192.168.0.%’;

grant 操作 MySQL 视图、查看视图源代码 权限。

grant create view on testdb.* to developer@’192.168.0.%’;

grant show view on testdb.* to developer@’192.168.0.%’;

grant 操作 MySQL 存储过程、函数 权限。

grant create routine on testdb.* to developer@’192.168.0.%’; -- now, can show procedure status

grant alter routine on testdb.* to developer@’192.168.0.%’; -- now, you can drop a procedure

grant execute on testdb.* to developer@’192.168.0.%’;

10>.grant 普通 DBA 管理某个 MySQL 数据库的权限。

grant all privileges on testdb to dba@’localhost’

其中，关键字 “privileges” 可以省略。

11>.grant 高级 DBA 管理 MySQL 中所有数据库的权限。

grant all on *.* to dba@’localhost’

12>.MySQL grant 权限，分别可以作用在多个层次上。

1. grant 作用在整个 MySQL 服务器上：

grant select on *.* to dba@localhost; -- dba 可以查询 MySQL 中所有数据库中的表。

grant all on *.* to dba@localhost; -- dba 可以管理 MySQL 中的所有数据库

2. grant 作用在单个数据库上：

grant select on testdb.* to dba@localhost; -- dba 可以查询 testdb 中的表。

3. grant 作用在单个数据表上：

grant select, insert, update, delete on testdb.orders to dba@localhost;

4. grant 作用在表中的列上：

grant select(id, se, rank) on testdb.apache_log to dba@localhost;

5. grant 作用在存储过程、函数上：

grant execute on procedure testdb.pr_add to ’dba’@’localhost’

grant execute on function testdb.fn_add to ’dba’@’localhost’

注意：修改完权限以后 一定要刷新服务，或者重启服务，刷新服务用：FLUSH PRIVILEGES。

权限表
权限	说明
all
alter
alter routine	使用alter procedure 和drop procedure
create
create routine	使用create  procedure
create temporary tables	使用create temporary table
create  user
create view
delete
drop
execute	使用call和存储过程
file	使用select into outfile  和load data infile
grant option	可以使用grant和revoke
index	可以使用create index 和drop index
insert
lock tables	锁表
process	使用show full processlist
reload	   使用flush
replication client	服务器位置访问
replocation slave	由复制从属使用
select
show databases
show view
shutdown	使用mysqladmin shutdown 来关闭mysql
super
update
usage	无访问权限



