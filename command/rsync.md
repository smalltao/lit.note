1、rsync、 remote、synchronize 远程同步命令，可以保持原文件时间、权限、软硬链接等附加信息
2、架设 rsync 服务器 写一个配置文件 rsync.conf 可以参考rsync.samba.org的说明文档
3、安装：下载：http://rsync.samba.org
    sudo apt-get install rsync debian ubuntu上安装
    yum install rsync redhat上 
    rpm -ivh rsync rpm 包安装 

    源码包安装：
        tar -zxvf rsync-xxx.tar.gz
        cd rsync.xxx
        ./configure --prefix=/usr make && make install
4、 配置文件 rsync 主要有三个配置文件 
    rsyncd.conf 主配置文件
    rsyncd.secrets 密码文件 
    rsyncd.motd 服务器信息   rsyncd.conf 配置文件在/etc/rsyncd.conf 默认不存在，自行创建
    
    touch /etc/rsyncd.conf 创建 rsync 服务配置信息
    touch /etc/rsyncd.secrets 创建用户密码文件
    chmod 600 /etc/rsyncd.secrets 设置权限为root拥有 
5、设置 rsyncd.conf 配置文件 
