package com.thread.concurrent.phaseronadvance;

import java.util.concurrent.Phaser;

/**
 * <p>Title:      自定义phaser. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 15:46
 */
public class MyPhaser extends Phaser {

    /**
     * 根据传入参数的phase值调用不同的操作
     * phase是0 调用studentsArrived()方法
     * phase是1 调用finishFirstExercise()方法
     * phase是2 调用finishSecondExercise()方法
     * phase是3 调用finishExam()方法
     * 如果不是上述值，返回true 表示phaser 已经终止
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0 :
                return studentsArrived();
            case 1 :
                return finishFirstExercise();
            case 2 :
                return finishSecondExercise();
            case 3 :
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.printf("所有学生都已经答完试卷 \n");
        return false;
    }

    private boolean finishSecondExercise() {
        System.out.printf("所有学生都完成了第二道题。 \n");
        System.out.printf("开始做第三道题 \n");
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("所有学生都完成了第一道题。 \n");
        System.out.printf("开始做第二道题 \n");
        return false;
    }

    //false 表示phaser开始执行
    private boolean studentsArrived() {
        System.out.printf("学生已经到场，准备考试。。。");
        System.out.printf("我们有学生：%d 名 \n" , getRegisteredParties());
        return false;
    }
}
