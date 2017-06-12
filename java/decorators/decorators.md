# 装饰器 小项目必备

## 配置
> 修改WEB-INF/web.xml 文件，增加包装器配置

```
<filter>
    <filter-name>sitemesh</filter-name>
    <filter-class>
        com.opensymphony.module.sitemesh.filter.PageFilter
    </filter-class>
</filter>
<filter-mapping>
    <filter-name>sitemesh</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

> 修改maven的pom 文件，增加依赖

```
<dependency>
    <groupId>opensymphony</groupId>
    <artifactId>sitemesh</artifactId>
    <version>2.2.1</version>
</dependency>
```

> 在WEB-INFO 下引入装饰器文件
```
decorators.xml
site-mesh.xml
```


