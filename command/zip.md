# 压缩相关命令
## tar 命令

> 必须的参数（三个参数不能共存）：

1. -c : 创建压缩文件 代表 create
2. -x : 解压缩文件
3. -t : 查看压缩包里的文件

> 辅助参数：

1. -z : 用gzip压缩/解压缩
2. -j : 用bzip2 压缩/解压缩
3. -v : 显示压缩/解压缩的进度条
4. -f : 使用档名（注意： f后面不要接参数，也就是-zxfv 是不对的，要写成-zxvf）

> 实例

```
1. 解压一个文件：
tar -zxvf abc.tar.bz2
2. 创建压缩文件
tar -zcvf file1 file2 file3
```
## rar 解压缩
> rar 格式的解压缩实例：
> 由于rarlinux 是收费软件，需要先[下载](www.rarsoft.com)

```
tar -zxvf rarlinux.tar.gz
cd ./rarlinxu
make && make install

unrar e filename.rar
```

## 7z 压缩
> 安装 7z 文件压缩实例
```
yum install p7zip
or
apt-get install p7zip
# 解压缩
7z x filename.7z
```

## zip 压缩/解压缩
```
# 压缩 当前目录内容为xxx.zip
zip -r xxx.zip
# 解压缩xxx.zip 到当前目录
unzip file.zip

```




