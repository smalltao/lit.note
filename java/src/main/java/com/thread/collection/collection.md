# java并发应用集合

> 阻塞式集合： 添加或者移除数据的方法，当集合已经满或者为空时，被调用的添加或者移除方法就不能立即执行，那么调用这个方法的线程将被阻塞，一直到该方法可以被成功执行

> 非阻塞式集合： 添加或者移除数据的方法，如果方法不能被立即执行，则返回空或者抛出异常，但是调用这个方法线程不会被调用

1. 非阻塞式列表：ConcurrentLinkedDeque 类
2. 阻塞式列表: LinkedBlockingDeque 类
3. 用户数据生成和消费的阻塞式列表对应的实现类 LinkedTransferQueue 类
4. 按优先级排序的阻塞式列表对应的实现类 PriorityBlockingQueue 类
5. 带有延迟列表元素的阻塞式列表的实现类 Delayqueue 类
6. 非阻塞式可遍历映射对应的实现类 ConcurrentSkipListMap 类
7. 随机数字对应的实现类 ThreadLocalRandom 类                         
8. 原子变量对应的实现类 AtomicLong 和 AtomicIntegerArray 类

