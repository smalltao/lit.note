##  不重启系统改变日志输出级别
```
log4j.logger.project.javase.com.martin.lang.Product=INFO,A
log4j.appender.A=org.apache.log4j.ConsoleAppender
log4j.appender.A.layout=org.apache.log4j.PatternLayout
log4j.appender.A.layout.ConversionPattern=%-d{yyyy-MM-dd HH:mm:ss,SSS} [%c]-[%p] %m%n
```

> spring也会加载log4j.properties，如果加载不到就往控制台打log信息Spring提供了一个Log4jConfigListener，本身就能通过web.xml中配置来指定位置加载log4j配置文件和log输出路径，注意该listener需要放在spring的Listener之前。

> 事实上，Log4jConfigListener更适合log4j在web工程中使用，原因如下：

1. 动态的改变记录级别和策略，不需要重启Web应用，如《Effective Enterprise Java》所说。
2. 把log文件定在 /WEB-INF/logs/ 而不需要写绝对路径。因为 系统把web目录的路径压入一个叫webapp.root的系统变量。这样写log文件路径时不用写绝对路径了.log4j.appender.logfile.File=${webapp.root}/WEB-INF/logs/myfuse.log
3. 可以把log4j.properties和其他properties一起放在/WEB-INF/ ，而不是Class-Path。
4. log4jRefreshInterval为60000表示开一条watchdog线程每60秒扫描一下配置文件的变化;

> 在web.xml 添加

```
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/applicationContext.xml</param-value>
</context-param>
<context-param>
    <param-name>log4jConfigLocation</param-name>
    <param-value>/WEB-INF/log4j.properties</param-value>
</context-param>
<context-param>
    <param-name>log4jRefreshInterval</param-name>
    <param-value>6000</param-value>
</context-param>

<listener>
    <listener-class>
        org.springframework.web.util.Log4jConfigListener
    </listener-class>
</listener>
<listener>
    <listener-class>
        org.springframework.web.context.ContextLoaderListener
    </listener-class>
</listener>
```

## 配置指定类中指定日志打印到指定位置
```
# 将指定日志输出到单独文件中
log4j.logger.time=debug, time
log4j.appender.time=org.apache.log4j.DailyRollingFileAppender
//每天一个日志
log4j.appender.time.Append=true
//日志文件是否追加 ，可选参数Append配置是否在原文件内容的基础上追加日志。如果为false，Logger 初始化时会先清掉文件内容，也就是说每次重启程序，原来的日志会丢失。如果为true，日志文件会越来越大。默认为true
log4j.appender.time.DatePattern='_' yyyy-MM-dd-HH-mm
//这里就是定义的时间格式，如果时间定义到分钟（mm）就是每分钟生成一个日志文件，而这里定义的这个格式就是 日志名后缀
log4j.appender.time.File=/logs/dcode/time.log
//日志输出录路径
log4j.appender.time.layout=org.apache.log4j.PatternLayout
//日志中输出的日志的格式
log4j.appender.file3.Encoding=UTF-8
//日志输出编码
log4j.appender.time.layout.ConversionPattern=[%-5p] %d{yyyy-MM-dd HH:mm:ss} %t %c - %m%n
//定义的日志格式
```

```
private static Logger logger = Logger.getLogger("time");
```

## mybatis 日志输出配置

```
log4j.logger.java.sql.ResultSet=INFO
log4j.logger.org.apache=INFO
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
log4j.logger.java.sql.ResultSet=INFO
```

## hibernate 日志输出配置

```
log4j.logger.org.hibernate=INFO
log4j.logger.org.hibernate.SQL=trace
log4j.logger.org.hibernate.HQL=trace
log4j.category.org.hibernate.type=trace
log4j.logger.org.springframework=INFO
log4j.rootLogger=INFO, CONSOLE ,PRODUCT,PRODUCT-ERROR
```

## 子日志是否输出到终端配置
```
log4j.additivity.org.apache=true
如果additivity为false，那么只生成一个rollinglogfile.log的日志文件，屏幕上没有日志信息输出
```



