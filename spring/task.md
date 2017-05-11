# spring task ��ʱ�������� 
> ע�ⷽʽ 
> ���÷�ʽ 

## ע�ⷽʽ
1. �� `spring` �����ļ�(`applicationContext.xml` ����`{project_name}_servelt.xml`)�е�schema���������� 
`xmlns:task="http://www.springframework.org/schema/task"`
`http://www.springframework.org/schema/task`
`http://www.springframework.org/schema/task/spring-task-3.0.xsd`

2. ������ʱ��  `<task:annotation-driven/>`

3. ����Ҫִ�ж�ʱ���������ִ�ж�ʱ����ķ����ϼ��� 
``` bash 
@Scheduled(cron="0 33/3 * * * ?") //ÿСʱ��33���ӿ�ʼִ�У�ÿ3����ִ��1��
public void start() throws ServletException {
    // ��һЩ��
}
```

## ʱ����ʽ
1. fixedRate: ÿ�� `N` ��ִ��һ�η���
``` bash 
@Scheduled(fixedRate=2000)  // ÿ��2��ִ��һ��
public void scheduleMethod(){  
        // ��һЩ��
}  
```

2. fixedDelay: ��һ�η���ִ�����֮���ӳ� `N` ������ִ�и÷���

3. cron: ��ϸ���÷�����ʲôʱ��ִ�У� cron ���ʽ  
``` bash 
@Scheduled(cron="0 0 0 * * SAT")  
public voidarchiveOldSpittles() {  
    //��һЩ��
}
```
## cron ��ϸ˵��



