## 等待多个并发事件的完成
> 在完成一组正在其他线程执行的操作之前，它允许线程一直等待，这个类使用整数进行初始化，整个整数就是线程需要等待完成的操作的数目
> 当一个线程要等待某些操作先执行时，需要调用await() 方法，这个方法让线程进入休眠直到等到所有操作都完成，当某一个操作完成之后，它调用countDown（）将countDownLathc类内容的计数器减1，当计算器变成1的时候，CountDownLathc类将唤醒所有调用await()方法而进入休眠的线程

