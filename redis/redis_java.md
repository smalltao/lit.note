# redis-java java 集成redis 整理自 [redis向导](https://www.tutorialspoint.com/redis/redis_java.htm)
## 安装redis驱动
1. [下载](http://repo1.maven.org/maven2/redis/clients/jedis/2.1.0/jedis-2.1.0-sources.jar)
2. 将下载的jar放置到classpath

> 也可以配置 `pom` 文件
```
<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
</dependency>
```

## 链接到redis 服务
```
import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main (String[] args) {
        // 连接到redis服务器
        Jedis jedis = new Jedis("localhost");
        System.out.println("链接服务器成功");
        //检测那个服务器正在运行
        System.out.println("正在运行的服务： " + jedis.ping());
    }
}
```
## 编译运行
```
$ javac RedisJava.java
$ java RedisJava
链接服务器成功
正在运行的服务: PONG
```
## 简单例子
```
import redis.clients.jedis.Jedis;

public class RedisStringJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("链接服务器成功");
        // 设置值
        jedis.set("tutorial-name", "Redis tutorial");
        // 取值
        System.out.println("取的的值： " + jedis.get("tutorialname"));
    }
}
```
## 集合的例子
```
import redis.clients.jedis.Jedis;

public class void main(String[]) args {
     Jedis jedis = new Jedis("localhost");

     //存储数据使用redis list
     jedis.lpush("tutorial-list", "Redis");
     jedis.lpush("tutorial-list", "Mongodb");
     jedis.lpush("tutorial-list", "Mysql");
     //取的数据
     List<String> list = redis.lrange("tutorial-list", 0, 5);

     for(int i =0; i<list.size(); i++) {
        System.out.println("在redis中存储的字符串是：" + list.get(i));
     }
}

```
## 遍历 `key`
```
import redis.clients.jedis.Jedis;

public class RedisKeyJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        List<String> list = jedis.keys("*");
        for(int i = 0; i<list.size(); i++) {
           System.out.println("List of stored keys:: "+list.get(i));
        }
    }
}
```

