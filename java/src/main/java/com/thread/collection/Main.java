package com.thread.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/25 0025 21:58
 */
public class Main {

    public static void main(String[] args) {

        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();

        Thread thread[] = new Thread[100];

        for (int i = 0; i < 100; i++) {
            AddTask addTask = new AddTask(list);

            thread[i] = new Thread(addTask);
            thread[i].start();
        }

        System.out.printf("main: %d 个addTask 已经启动\n", thread.length);

        //等待所有线程结束
        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("main： list的大小为：%d \n", list.size());


        for (int i = 0; i < thread.length; i++) {
            PollTask pollTask = new PollTask(list);
            thread[i] = new Thread(pollTask);
            thread[i].start();
        }

        System.out.printf("main: %d pollTask 已经结束 \n", thread.length);

        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("main： list的大小为：%d \n", list.size());
    }
}
