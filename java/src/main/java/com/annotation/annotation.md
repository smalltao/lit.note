## 注解使用
### @interface
> 用于定义一个注解
### @Retention 可以用来修饰注解，是注解的注解，称为元注解
1. RetentionPolicy.SOURCE —— 这种类型的Annotations只在源代码级别保留,编译时就会被忽略
2. RetentionPolicy.CLASS —— 这种类型的Annotations编译时被保留,在class文件中存在,但JVM将会忽略
3. RetentionPolicy.RUNTIME —— 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
```
@Retention(RetentionPolicy.RUNTIME)//声明注解，jvm运行时PersonInfo注解能被读出
@Target(ElementType.TYPE)
public static  @interface PersonInfo {
    String name();
    int age();
    boolean sex();
}
```

### @Target 决定注解使用的地方