package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title:      打印队列类. </p>
 * <p>Description 使用ReentrantLock实现了一个临界区，保证同一时间只有一个对象能够访问临界区资源 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     2017/6/21 10:05
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();


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

    }

}