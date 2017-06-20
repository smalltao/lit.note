package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印队列类
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();


    private void printJoib(Object document) {
        queueLock.lock();

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "打印队列：输出任务的执行时间：" + (duration / 1000) + "毫秒");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}