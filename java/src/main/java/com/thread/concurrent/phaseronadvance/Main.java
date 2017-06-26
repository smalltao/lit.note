package com.thread.concurrent.phaseronadvance;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     2017/6/22 17:10
 */
public class Main {
    public static void main(String[] args) {

        MyPhaser phaser = new MyPhaser();

        Student students[]  = new Student[5];
        for (int i = 0; i < 5; i++) {
            students[i] = new Student(phaser);
            //将每个学生对象注册到phaser
            phaser.register();
        }

        Thread threads[] = new Thread[students.length];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i], "student " + i);
            threads[i].start();
        }

        //等待所有学生线程完成
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("主方法：phaser 已经结束 %s \n ", phaser.isTerminated());

    }
}
