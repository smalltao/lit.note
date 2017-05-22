# redis 实战笔记 
1. 安装
```
下载: wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/redis/redis-2.6.14.tar.gz
解压：tar -xzvf redis-2.6.14.tar.gz 
编译：# make
启动：# src/redis-server
将 redis 作为 linux 服务随机启动
# vim /etc/rc.local文件，末尾追加 /root/4setup/redis-2.6.14/src/redis-server
验证： 新开session 输入src/redis-cli 
停止：src/redis-cli shutdown
```

2. 操作数据库
```
插入：
# set name wwl
查询：
# get name 
删除:
del name  删除成功是: 1 删除失败是: 0 
验证键是否存在：
exists name 存在是：1 不存在是：0
```
## strings strings 类型及操作
> strings 类型是二进制安全的 ，所以可以存储任何类型的数据，包括jpg图片或者序列化对象，内部实现可以看做是byte数组，最大上限是1G字节
```
struct sdshdr {
long len;
long free;
char buf[];
}
len 是buf 数组的长度
free 是数组中剩余可用字节数
buf 是一个char数组，用于存储实际的字符串内容，
```
1. `set` 
> 设置`key` 对应的值为`string` 类型的`value`
```
例如添加一个name=taotao 的键值对，可以这样做：
$ set set name taotao
ok
```
2. `setnx`
> 设置`key` 对应的值为`string` 类型的`value`。 如果`key` 已经存在，则返回 0，nx 是 not exist 的意思。
```
$ get name 
"taotao"
$ setnx name tatao_new
(integer) 0
$ get name 
"taotao"
因为原来有一个对应的值，所以本次修改不生效。且返回码是0。
```
3. `setex`
> 设置 `key`对应的值为`string` 类型的`value`, 并指定此键值对应的有效期
```
添加一个haircolor=red 的键值对，并指定它的有效期是10 秒
$ setex haircolor 10 red
ok
get haircolor 
"red"
$ get haircolor
(nil)
10 秒之后 就获取不到了
```
4. `setrange`
> 设置指定`key` 的`value1` 值的子字符串。
```
例如我们希望将taotao 的 qq邮箱替换为gmail 邮箱
$ get name 
"12345678@qq.com"
$ setrange name 9 gmail.com
(integer) 18
$ get name 
"1234678@gmail.com"
其中 9 是指下标9 （包含9）的字符开始替换

```
5. `mset` 
> 一次设置多个`key`的值， 成功返回ok 表示所有的值都设置了，失败返回0 ，表示没有设置任何值
```
redis 127.0.0.1:6379> mset key1 tao key2 taotao2
OK
redis 127.0.0.1:6379> get key1 
"tao"
redis 127.0.0.1:6379> get key2 
"taotao2"
redis 127.0.0.1:6379> 
```
6. `msetnx` 
> 一次设置对个 `key` 的值，成功返回ok，表示素有值都设置了，失败返回 0， 表示没有任何值被设置了，但是不会覆盖已经存在的key
```
redis 127.0.0.1:6379> get key1 
"tao"
redis 127.0.0.1:6379> get key2 
"taotao2"
redis 127.0.0.1:6379> msetnx key2 tao2_new key3 tao3 
(integer) 0
redis 127.0.0.1:6379> get key2 
"taotao2"
redis 127.0.0.1:6379> get key3 
(nil)
redis 127.0.0.1:6379> 
这条命令返回0，那么里面操作都会回滚，都不会被执行。
```
7. `get`
> 获取`key` 对应的string 值，如果key 不存在返回nil
```
redis 127.0.0.1:6379> get name 
"12345678@gmail.com"
redis 127.0.0.1:6379> get tao
(nil)
redis 127.0.0.1:6379> 
当不存在时，返回nil
```
8. `getset`
> 设置`key`的值，并返回 `key` 的旧值 
```
redis 127.0.0.1:6379> getset name oat
"12345678@gmail.com"
redis 127.0.0.1:6379> get name 
"oat"
redis 127.0.0.1:6379> 
如果 key不存在 ，返回nil
redis 127.0.0.1:6379> getset name1 oat
(nil)
redis 127.0.0.1:6379> 
```
9. `getrange`
> 获取指定`key` 的`value` 的子字符串
```
redis 127.0.0.1:6379> get name 
"12345678@qq.com"
redis 127.0.0.1:6379> getrange name 0 7
"12345678"
左面下标从0 开始
redis 127.0.0.1:6379> getrange name -6 -1
"qq.com"
右面下边从 -1 开始
redis 127.0.0.1:6379> getrange name 7 100
"8@qq.com"
下标超过字符串最大长度是，将默认使用同方向最大下标
redis 127.0.0.1:6379> 
```


















