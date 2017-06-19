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
log4j.appender.time.File=/logs/dcode/time.log
log4j.appender.time.layout=org.apache.log4j.PatternLayout
log4j.appender.time.layout.ConversionPattern=[%-5p] %d{yyyy-MM-dd HH:mm:ss} %t %c - %m%n
```

```
private static Logger logger = Logger.getLogger("time");
```

