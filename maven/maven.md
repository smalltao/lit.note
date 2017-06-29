## 国内maven 仓库

```
<mirror>  
   <id>nexus-aliyun</id>  
   <mirrorOf>*</mirrorOf>  
   <name>Nexus aliyun</name>  
   <url>http://maven.aliyun.com/nexus/content/groups/public</url>  
</mirror>  
```

## maven 插件解释

### maven-surefire-plugin
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.4.2</version>
    <configuration>
        <skipTests>true</skipTests>
        <!-- 输出编码 -->
        <argLine>-Dfile.encoding=UTF-8</argLine>
    </configuration>
</plugin>
```

> Maven 通过 Maven Surefire Plugin插件执行单元测试。（通过Maven Failsafe Plugin 插件执行集成测试）

1. 在pom.xml 中配置 JUnit,TestNG 测试框架的依赖，即可自动识别和运行src/test 目录下利用该框架编写的测试用例。
2. surefire也能识别和执行符合一定命名约定的普通类中的测试方法（POJO测试）。
3. 生命周期中 test 阶段默认绑定的插件目标就是surefire中的test目标，无需额外配置，直接运行mvn test就可以。

#### 常用通用配置

> 跳过测试阶段

```
<skipTests>true</skipTests>
或者
# mvn install -DskipTests
```

> 忽略测试失败
> Maven在测试阶段出现失败的用例时，默认的行为是停止当前构建，构建过程也会以失败结束。有时候（如测试驱动开发模式）即使测试出现失败用例，仍然希望能继续构建项目。

```
<testFailureIgnore>true</testFailureIgnore>
或者
mvn test -Dmaven.test.failure.ignore=true
```

> 包含和排除特定的测试类 surefire默认的查找测试类的模式如下：

```
1. **/Test*.java
2. **/*Test.java
3. **/*TestCase.java
```

> 自定义包含和排除模式，支持ant-style表达式和 正则表达式（%regex[...], 按.class文件匹配而不是.java）

```
<includes>
    <include>Sample.java</include>
    <include>%regex[.*[Cat|Dog].*Test.*]</include>
</includes>

<excludes>
    <exclude>**/TestCircle.java</exclude>
    <exclude>**/TestSquare.java</exclude>
</excludes>
```

> 运行指定的用例

1. 指定测试类

```
　mvn -Dtest=TestClassName test
　mvn -Dtest=TestCi*le test
　mvn -Dtest=TestSquare,TestCi*le test
```

2. 指定单个测试类中的多个方法（Junit4+, TestNG）　

```
　mvn -Dtest=TestCircle#mytest test
　mvn -Dtest=TestCircle#test* test
　mvn -Dtest=TestCircle#testOne+testTwo test   #(Surefire2.12.1+, Junit4.x+)
```

> 并发执行测试 mvn命令加-T选项，多模块项目的各个模块可以并行构建

> 两个方式：

1. 方法一
> 是使用 parallel 参数，在一个进程中执行多个线程。
Junit4.7+可用值有：methods, classes, both(classesAndMethods), suites, suitesAndClasses, suitesAndMethods, classAndMethods, all。Junit Runner必须继承自orig.junit.runners.ParentRunner或为指定@org.junit.runner.RunWith。
线程数配置：useUnlimitedThreads 为true，不限制线程数。useUnlimitedThreads 为false时可以使用threadCount和perCoreThreadCount参数。还可以通过threadCountSuites，threadCountClasses，threadCountMethods在不同粒度限制线程。parallelTestsTimeoutInSeconds和parallelTestsTimeoutForcedInSeconds参数设置线程的超时时间。Junit中@NotThreadSafe注解的内容会单线程执行，避免并发。

2. 方法二
> 是使用forkCount参数，创建多个测试进程。
如果forkCount参数值后加C，表示乘以CPU核数（如forkCount=2.5C）。reuseForks表示一个测试进程执行完了之后是杀掉还是重用来继续执行后续的测试。 默认配置为forkCount=1/reuseForks=true。进程的测试单元是class，逐个class的传递给测试进程。
可以用systemPropertyVariables 传入系统参数（mvn test -D...或配置元素），也可以使用argLine传入JVM选项。argLine或者systemPropertyVariables配置里中也能用${surefire.forkNumber}占位符，代表每个进程自己的fork编号（1...n），用来向每个进程传入独立的资源配置（forkCount=0时，该占位符值为1）。

> 多种并行方式组合
只要forkCount不为0，就可以和-T组合。
forkCount=0, 或forkCount=1/reuseForks=true，可以和parallel自由组合。
forkCount的测试进程是按类为单位执行的，测试类整个整个的传到测试进程中执行。reuseForks=false或forkCount>1时，就会使用独立的测试进程，所以parallel=classes就失效了。但是还是可以组合parallel=methods/threadCount=n指定每个测试进程里的并发线程数。

>  TestNG TestNG是Java中的一个测试框架， 类似于JUnit 和NUnit,TestNG比JUnit功能强大的多。  测试人员一般用TestNG来写自动化测试。  开发人员一般用JUnit写单元测试。
1. TestNG默认查找执行test包下的*Test.java。Pom.xml中添加TestNG依赖就能执行testng测试。
2. 指定SuiteXML文件
```
<suiteXmlFiles>
      <suiteXmlFile>testng.xml</suiteXmlFile>
 </suiteXmlFiles>
```
3. 为TestNG @Parameters 注解提供参数
```
<systemPropertyVariables>
     <propertyName>firefox</propertyName>
 </systemPropertyVariables>
```
4. 指定group
```
<groups>functest,perftest</groups>
```
5. 指定Listeners和Reporters
```
TestNG支持在测试时附加自定义的listener, reporter, annotation transformer, method interceptor。默认会使用基本的listener生成HTML和XML报告。
Listener实现org.testng.ITestListener接口,会在测试开始、通过、失败等时刻实时发送通知。
Reporter实现org.testng.IReporter接口，在整个测试运行完毕之后才会发送通知，参数为对象列表，包含整个测试的执行结果状况。
```
```
<properties>
      <property>
          <name>usedefaultlisteners</name>
          <value>false</value> <!-- disabling default listeners is optional -->
      </property>
      <property>
          <name>listener</name>
          <value>com.mycompany.MyResultListener,com.mycompany.MyAnnotationTransformer,com.mycompany.MyMethodInterceptor</value>
      </property>
      <property>
          <name>reporter</name>
          <value>listenReport.Reporter</value>
      </property>
</properties>
```


### maven-compiler-plugin 资源过滤插件 可以过滤或者迁移资源
> maven用可以利用如下配置进行资源过滤
```
<build>
    <!-- 主资源目录 -->
    <resources>
        <resource>
            <!-- 设定主资源目录  -->
            <directory>src/main/resources</directory>
            <!-- maven default生命周期，process-resources阶段执行maven-resources-plugin插件的resources目标处理主资源目下的资源文件时，只处理如下配置中包含的资源类型
            <includes>
                <include>*.xml</include>
            </includes>
             -->
            <!-- maven default生命周期，process-resources阶段执行maven-resources-plugin插件的resources目标处理主资源目下的资源文件时，不处理如下配置中包含的资源类型（剔除下如下配置中包含的资源类型）
            <excludes>
                <exclude>*.xml</exclude>
            </excludes>
            -->
            <!-- maven default生命周期，process-resources阶段执行maven-resources-plugin插件的resources目标处理主资源目下的资源文件时，指定处理后的资源文件输出目录，默认是${build.outputDirectory}指定的目录
            <targetPath>d:/</targetPath>
               -->
            <!-- maven default生命周期，process-resources阶段执行maven-resources-plugin插件的resources目标处理主资源目下的资源文件时，是否对主资源目录开启资源过滤 -->
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

> 利用这个特性可以解决开发跟生产环境数据库连接配置等问题，但有时这个特性并不符合实际。比如，开发环境我们使用tomcat应用服务器，使用dbcp数据源，但是生产则使用jboss应用服务器，使用jndi来管理数据源。开发环境跟生产环境spring容器中对于id=dataSources的bean的配置如下所示

1. 开发环境
```
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
    <property name="url" value="jdbc:oracle:thin:@localhost:1521:gbst" /> <!-- 开发库 -->
    <property name="username" value="admin" />
    <property name="password" value="admin" />
    <property name="initialSize" value="1"/>
    <property name="maxActive" value="2"/>
</bean>
```
2. 生成环境
```
<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
    <property name="jndiName">
        <value>PrdDS</value>
    </property>
    <property name="resourceRef">
        <value>false</value>
    </property>
</bean>
```
> 像这种情况，靠<build><resources><resource>的资源过滤功能就不太实用了，这时可以采用resources插件的copy-resources目标进行资源copy，来达到分别配置开发环境与生产环境的数据库连接信息及其它一些配置（缓存服务器地址、短信网关系统连接配置等等），下面分步骤来达到这一目的。
> 详细看这个博客吧 http://www.tuicool.com/articles/JfaA7r

### tomcat7-maven-plugin

1. 配置Tomcat Manager 用户
> 重新解压缩了一个新的tomcat7，打开Tomcat根目录下conf目录中的tomcat_user.xml，在<tomcat-users>节点下添加manager角色和用户，用于Tomcat Maven Plugin自动部署应用，xml代码如下
```
<tomcat-users>
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="alexgaoyh" password="alexgaoyh" roles="manager-gui, manager-script"/> <!-- 用户名和密码可以是其他字合法符串 -->
</tomcat-users>
```

2. Maven配置中添加Tomcat Server
> 打开Maven的settings.xml文件，如果是本地安装版Maven，文件为安装根目录下conf/settings.xml，如果使用的是Eclipse内置的Maven，则该文件为当前系统用户目录下的/.m2/setting.xml。在<servers>节点下配置Tomcat的Manager用户信息，xml代码如下：
```
<servers>
    <server>
       <id>Tomcat7</id>
       <username>alexgaoyh</username>
       <password>alexgaoyh</password>
     </server>
<servers>
```

3. 在Maven项目中添加Tomcat Service:
> 打开项目pom.xml文件，在<plugins>节点下添加Tomcat Maven Plugin插件，xml代码如下：

```
<build>
    <finalName>web</finalName>
    <plugins>
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
            <url>http://localhost:8080/manager/text</url> <!-- tomcat管理路径 -->
            <server>Tomcat7</server> <!-- 与settings.xml文件中Server的id相同 -->
            <path>/web</path> <!-- 应用的部署位置 -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

4.  部署到Tomcat7:
> 在project视图中选中项目，右键"Run As"->"Maven bulid"，执行tomcat7:deploy命令，即可完成部署，执行tomcat7:redeploy即可重新部署项目，tomcat7:undeploy卸载部署。



