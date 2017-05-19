# 使用junit 测试 Controller
> 使用mockmvc 对象模拟浏览器请求
## 依赖
> 项目依赖
```
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-context</artifactId>  
    <version>${spring.version}</version>  
</dependency>  
  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-webmvc</artifactId>  
    <version>${spring.version}</version>  
</dependency>
```
> 测试依赖
```
<dependency>  
    <groupId>junit</groupId>  
    <artifactId>junit</artifactId>  
    <version>${junit.version}</version>  
    <scope>test</scope>  
</dependency>  
<dependency>  
    <groupId>org.hamcrest</groupId>  
    <artifactId>hamcrest-core</artifactId>  
    <version>${hamcrest.core.version}/version>  
    <scope>test</scope>  
</dependency>  
<dependency>  
    <groupId>org.mockito</groupId>  
    <artifactId>mockito-core</artifactId>  
    <version>${mockito.core.version}</version>  
    <scope>test</scope>  
</dependency>  
  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-test</artifactId>  
    <version>${spring.version}</version>  
    <scope>test</scope>  
</dependency>  
```
## `xml` 风格配置
> spring-config.xml：加载非web层组件 
```
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
       xmlns:context="http://www.springframework.org/schema/context"  
       xsi:schemaLocation="  
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd  
       ">  
    <!-- 通过web.xml中的 org.springframework.web.context.ContextLoaderListener 加载的  -->  
    <!-- 请参考 http://jinnianshilongnian.iteye.com/blog/1602617  -->  
    <context:component-scan base-package="com.sishuok.mvc">  
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>  
    </context:component-scan>  
</beans>  
```
