package com.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title:      视频会议类 等待多个并发线程的完成. </p>
 * <p>Description 视频会议系统会等待所有参会者都到来才开始，可以用于翻译多个线程的合并 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 16:58
 */
public class VideoConference implements Runnable {

    private final CountDownLatch controller;

    // number 为线程需要等待完成的数目
    public VideoConference(int number) {
        this.controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("已经来的人：%s  ", name);

        controller.countDown();

        System.out.printf("还没有来的人：%d \n", controller.getCount());

    }

    @Override
    public void run() {

        System.out.printf("这是视频会议的人数：%d", controller.getCount());

        try {
            //等待直到所有与会者到齐
            controller.await();

            System.out.printf("所有参会者到齐，会议开始。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
