## 在集合点的同步 cyclicbarrier   ['saɪklɪk; 'sɪk-]['bærɪə] 功能上比countdownlatch 更加强大

> 允许两个或多个线程在某个点上进行同步
> CyclicBarrier 使用整型数进行初始化，这个数是需要在某个点需要同步的线程数
> 当一个线程到达指定的点后，它将调用await()方法等待其他线程，当调用await()方法后，CyclicBarrier类将阻塞这个线程并使之休眠到其他所有线程到达，当最后一个线程调用cyclicBarrier类的await()方法时，CyclicBarrier将唤醒所有等待的线程，然后这些线程将继续执行
