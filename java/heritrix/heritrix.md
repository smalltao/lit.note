# heritrix 爬虫使用笔记
> [文档]( https://webarchive.jira.com/wiki/display/Heritrix)
> [向导](https://webarchive.jira.com/wiki/display/Heritrix/Heritrix+3.0+and+3.1+User+Guide)
> [api](http://builds.archive.org/javadoc/heritrix-3.2.0/)

## 向导部分笔记
### [下载](http://builds.archive.org:8080/maven2/org/archive/heritrix/heritrix/3.1.1/heritrix-3.1.1-dist.zip)
> [限制抓取带框](https://github.com/lemurproject/clueweb12pp/wiki/squid)
```
unzip heritrix-3.1.1-dist.zip
```
### 安装 配置
```
1. 设置JAVA_HOME环境变量。
export JAVA_HOME=/usr/local/java/jre
2. 设置HERITRIX_HOME环境变量，该值设置为Heritrix的bin目录
export HERITRIX_HOME=/PATH/TO/HERITRIX
例如，Heritrix的与一个安装bin位于目录/home/user/heritrix3.1/bin如下将被配置。
export HERITRIX_HOME=/home/user/heritrix3.1
3. 在Heritirix启动文件上设置执行权限
chmod u+x $HERITRIX_HOME/bin/heritrix
要更改分配给Heritrix的内存量（Java堆大小），请设置JAVA_OPTS环境变量。以下示例将1GB的内存分配给Heritrix。
export JAVA_OPTS=-Xmx1024M
```

### 运行 htritrix 3.0 或 3.1
> Heritrix可以运行许多命令行选项。输入以下命令输出选项。
```
$HERITRIX_HOME/bin/heritrix --help
```
命令行选项 | 描述
--- | ---
-a --web-admin <arg> | 指定必须提供的授权用户名和密码以访问 Web UI。如果启动Web UI ，则需要此参数。<br />参数格式为<adminname>:<adminpassword> 例如： admin:admin <br /> 从Heritrix 3.1 起，如果提供-a 命令行选项的参数 以 “@” 开头的字符串，则其余字符串将被解释为包含操作员登录名和密码的本地文件名。<br />这为管理员用户名和密码添加了一个额外的保护层。
-b --web-bind-host <arg>| 指定要绑定到Web UI的主机名/ IP地址的逗号分隔列表。如果没有指定，Web UI将只能通过localhost / 127.0.0.1使用。您可以使用'/'作为“所有地址”的缩写。
-h，--help <arg>  | 显示使用信息。
-j，--jobs-dir | 显示作业目录。默认是./jobs。
-l，--logging-properties | 显示记录属性文件的完整路径（例如，conf/logging.properties）。如果存在，则该文件将用于配置Java日志记录。默认是./conf/logging.properties。
-p，--web-port <arg> | 指定Web UI将侦听的端口。
-r，--run-job <arg> | 指定在Heritrix启动时启动的就绪作业或配置文件名称。如果指定了配置文件名称，则配置文件将首先被复制到新的就绪作业，并且该就绪作业将被启动。截至Heritrix 3.1，此选项已被删除。
-s，--ssl-params <arg> | 指定密钥库路径，密钥库密码和HTTPS密码。用逗号分隔值，不包括空格。

> 要启用启用Web UI的Heritrix，请输入以下命令。Web UI的用户名和密码分别设置为“admin”和“admin”。
```
$HERITRIX_HOME/bin/heritrix -a admin:admin
```

### 基于web 的用户界面
1. 地址：HTTPS：//（heritrixhost）：8443
2. 必须使用-a or -web-admin命令行选项在启动时或通过设置heritrix.cmdline.admin系统属性来指定用户名和密码

### 安全注意事项
> 使用ssh 隧道
```
ssh -L localhost:9999:localhost:8443 crawloperator@crawler.example.com -N
```
> 如果您需要Heritrix的侦听端口绑定到公共地址，则可以使用'-b'命令行标志。作为参数，此标志将使用要使用的主机名/地址。'/'字符可用于指示所有地址。

### 爬取第一个程序的快速指南
> [详细使用](https://webarchive.jira.com/wiki/display/Heritrix/A+Quick+Guide+to+Running+Your+First+Crawl+Job)





