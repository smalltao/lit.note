# spring task 定时任务配置 
> 注解方式 
> 配置方式 

## 注解方式
1. 在 `spring` 配置文件(`applicationContext.xml` 或者`{project_name}_servelt.xml`)中的schema配置中增加 
`xmlns:task="http://www.springframework.org/schema/task"`
`http://www.springframework.org/schema/task`
`http://www.springframework.org/schema/task/spring-task-3.0.xsd`

2. 启动定时器  `<task:annotation-driven/>`

3. 在需要执行定时任务的类中执行定时任务的方法上加上 
``` bash 
@Scheduled(cron="0 33/3 * * * ?") //每小时的33分钟开始执行，每3分钟执行1次
public void start() throws ServletException {
    // 做一些事
}
```

## 时间表达式
1. fixedRate: 每隔 `N` 秒执行一次方法
``` bash 
@Scheduled(fixedRate=2000)  // 每隔2秒执行一次
public void scheduleMethod(){  
        // 做一些事
}  
```

2. fixedDelay: 当一次方法执行完毕之后，延迟 `N` 毫秒再执行该方法

3. cron: 详细配置方法在什么时候执行， cron 表达式  
``` bash 
@Scheduled(cron="0 0 0 * * SAT")  
public voidarchiveOldSpittles() {  
    //做一些事
}
```
## cron 详细说明



