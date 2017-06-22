package concurrent.phaseronadvance;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * <p>Title:      学生类，模拟学生考试. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 16:59
 */
public class Student implements Runnable {

    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        System.out.printf("%s 线程已经到达考场，花费：%s \n", Thread.currentThread().getName(), new Date());
        //休眠当前线程，并等待其他线程到达此此处
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s 正在做第一道题 %s \n", Thread.currentThread().getName(), new Date());
        doExercisel();
        System.out.printf("%s 已经完成第一道考题 %s \n", Thread.currentThread().getName(), new Date());
        //等待其他学生完成第一道考题
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s 正在做第二道题 %s \n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%s 已经完成第二道考题 %s \n", Thread.currentThread().getName(), new Date());
        //等待其他学生完成第二道考题
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s 正在做第三道题 %s \n", Thread.currentThread().getName(), new Date());
        doExercise3();
        System.out.printf("%s 已经完成第三道考题 %s \n", Thread.currentThread().getName(), new Date());
        //等待其他学生完成第第三道考题
        phaser.arriveAndAwaitAdvance();


    }

    private void doExercise3() {
        long duration = (long) (Math.random() * 10);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise2() {
        long duration = (long) (Math.random() * 10);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercisel() {
        long duration = (long) (Math.random() * 10);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
