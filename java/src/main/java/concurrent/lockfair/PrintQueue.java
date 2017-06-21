package concurrent.lockfair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title:      打印队列类. </p>
 * <p>Description 使用 ReentrantLock、ReentrantReadWriteLock公平模式和非公平模式  </p>
 * <p>fair等于true为公平模式：当很多线程等待锁时，锁将选择等待时间最长的线程</p>
 * <p>fair等于false为非公平模式：当很多线程等待锁时，锁将将选择等待锁的一个线程获得锁，选择没有任何约束，就是随机吧</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:05
 */
public class PrintQueue {

    // 公平模式
    private final Lock queueLock = new ReentrantLock(true);


    public void printJoib(Object document) {
        queueLock.lock();

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "打印队列：输出任务的执行时间：" + (duration / 1000) + "毫秒");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "打印队列：输出任务的执行时间：" + (duration / 1000) + "毫秒");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

    }

}