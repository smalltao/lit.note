# lombok  [消除冗长的 java 代码](http://projectlombok.org/  )

## 安装

1. 将 lombok.jar 复制到 myeclipse.ini / eclipse.ini 所在的文件夹目录下
2. 打开 eclipse.ini / myeclipse.ini，在最后面插入以下两行并保存：
    -Xbootclasspath/a:lombok.jar
    -javaagent:lombok.jar
3. 重启 eclipse / myeclipse

## 注解

```
@Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@Setter：注解在属性上；为属性提供 setting 方法
@Getter：注解在属性上；为属性提供 getting 方法
@Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
@NoArgsConstructor：注解在类上; 为类提供一个无参的构造方法
@AllArgsConstructor：注解在类上; 为类提供一个全参的构造方法
```

## 官方文档：https://projectlombok.org/features/all


## idea下使用

> 下载插件：https://github.com/mplushnikov/lombok-intellij-plugin/releases

> Plugins >> Install plugin from disk...

> annotation processors 激活idea的 注解进程
> 在Preference——Build,Execution,Deployment——Compiler——Annotation Processors中
> 下载 Lombok.jar 到指定目录下：/path/to/lombok.jar；
> Preferences -> Build, Execution, Deploymen -> Compiler：Shared build process VM options 填写： -javaagent:/path/to/lombok.jar；

> 或者
```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.6</version>
</dependency>
```


## 解释

名称 | 说明
--- | ---
val | 最终局部变量
@Data | 生成 @ToString，@EqualsAndHashCode， @Getter 在所有领域，@Setter所有非final字段，以及 @RequiredArgsConstructor！
