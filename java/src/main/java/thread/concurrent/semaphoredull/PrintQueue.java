package thread.concurrent.semaphoredull;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title:      使用信号量保护一个资源的多个副本. </p>
 * <p>Description 主类 </p> 一个打印队列，被三个不同的打印机使用
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:05
 */
public class PrintQueue {


    private final Semaphore semaphore;

    // 存放打印机状态
    private boolean freePrinters[];

    //用来保护freePrinters数组的访问
    private Lock lockPrinters;

    public PrintQueue() {
        this.semaphore = new Semaphore(3);
        this.freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            this.freePrinters[i] = true;
        }
        this.lockPrinters = new ReentrantLock();
    }

    public void printJoib(Object document) {

        try {
            //获取信号量
            semaphore.acquire();
            //获取打印机编号
            int assingedPrinter = getPrinter();
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "打印队列：使用打印机："+ assingedPrinter +"输出任务的执行时间：" + (duration / 1000) + "毫秒");
            Thread.sleep(duration);

            freePrinters[assingedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }

    /**
     * <p>Title:      获得打印机编号. </p>
     * <p>Description </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/21 15:41
     */
    public int getPrinter() {
        //存储打印机编号
        int ret = -1;

        try {
            lockPrinters.lock();
            //在freePrinters数组中找到第一个true，并将索引保存到打印机编号
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i]=false;
                    break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }
        return ret;
    }
}