常用命令
## 获取指定日期cost
grep '\[2017-04-20.*\]' stdout.log.201704 | grep 'cost=[1-9]\{4,\}' | sed -n '{s/.*,cost=/cost=/;s/,url=.*/ /p}' 

## alternatives是Linux下的一个功能强大的命令。只能在root权限下执行。如系统中有几个命令功能十分类似，却又不能随意删除，那么可以用 alternatives 来指定一个全局的设置。
```
alternatives --set java  /usr/lib/jvm/jre-1.8.0-oracle.x86_64/bin/java
alternatives --set javac /usr/lib/jvm/java-1.8.0-oracle.x86_64/bin/javac
```
## 修改环境变量
> 临时修改

```
export PATH=/opt/STM/STLinux-2.3/devkit/sh4/bin:$PATH
```
> 长期有效

```
1.1 修改 /etc/profile
1.2 修改 ~/.bashrc
```
> 在Tomcat的bin目录下的catalina.sh添加

```
export JAVA_HOME="/usr/sso/jdk1.6.0"
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOMR/bin
```

## 查看系统位数
```
1. getconf LONG_BIT or getconf WORD_BIT
2. file /bin/ls
3. 查看版本号： lsb_release -a
```
## 查找大文件
```
超过800m的文件名称
find . -type f -size +800M
超过800m的文件的详细信息
find . -type f -size +800M  -print0 | xargs -0 ls -l
超过800m的文件的具体大小
find . -type f -size +800M  -print0 | xargs -0 du -h
找到之后排序
find . -type f -size +800M  -print0 | xargs -0 du -h | sort -nr
```

## 查找大目录
```
du -h --max-depth=1
du -h --max-depth=2 | sort -n
du -hm --max-depth=2 | sort -n
只显示前12条
du -hm --max-depth=2 | sort -nr | head -12

```


