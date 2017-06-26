package com.thread.executor.delay;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      主类. </p>
 * <p>Description 延迟执行任务</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:11
 */
public class Main {

    public static void main(String[] args) {

        //参数1 表示创建一个执行器
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("main : 开始于 ： %s \n", new Date());
        for (int i = 0; i < 5; i++) {
            Task tak = new Task("task " + i);
            // 将要执行的任务，执行任务前等待的时间，等待时间的单位
            executor.schedule(tak, i + 1, TimeUnit.SECONDS);
        }
        executor.shutdown();

        //等待所有任务结束
        try {
            // 最长等待 1 天
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("main： 结束：%s \n", new Date());
    }
}
