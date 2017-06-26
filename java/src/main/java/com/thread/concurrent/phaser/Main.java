package com.thread.concurrent.phaser;

import java.util.concurrent.Phaser;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 15:04
 */
public class Main {

    public static void main(String[] args) {
        //指定阶段同步的任务是三个
        Phaser phaser = new Phaser(3);


        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Users\\litaoos2862\\Documents", "log", phaser);

        Thread systemThread = new Thread(system);
        systemThread.start();

        Thread appsThread = new Thread(apps);
        appsThread.start();

        Thread documentThread = new Thread(documents);
        documentThread.start();

        //等待三个线程结束
        try {
            systemThread.join();
            appsThread.join();
            documentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("phaser 是否终止：" + phaser.isTerminated());
    }
}
