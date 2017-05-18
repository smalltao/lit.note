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
```bash

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
```bash
















