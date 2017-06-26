package com.thread.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * <p>Title:      使用信号量保护资源同步，可以保护单一共享资源，单一临界区. </p>
 * <p>Description 计数器大于0 代表有可以使用的资源，计数器等于0 代表所有共享资源已经被其他线程使用了</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:05
 */
public class PrintQueue {


    private final Semaphore semaphore;

    public PrintQueue() {
        // 参数1 表示二进制信号量
        this.semaphore = new Semaphore(1);
    }

    public void printJoib(Object document) {

        try {
            //获取信号量
            semaphore.acquire();
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "打印队列：输出任务的执行时间：" + (duration / 1000) + "毫秒");
            Thread.sleep(duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }
}