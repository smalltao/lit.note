## 运行多个任务并处理所有结果

> 如果想要等待任务结束，可以使用如下两种方法

1. 如果任务结束，那么Future接口的isDone()方法将返回true
2. 如果调用shutDown()方法后，ThreadPoolExcutor类的awaitTermination()会将线程休眠，直到所有的任务执行结束

3. ThreadPoolExcutor类的 invokeAll()方法允许发送一个任务列表给执行器，并等待所有任务执行完成





