package com.thread.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>Title:      查找类. </p>
 * <p>Description 查找矩阵中指定行的某个数 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 12:06
 */
public class Searcher implements Runnable {

    // firstRow 和 lastRow 指定查找范围
    private int firstRow;

    private int lastRow;

    private MatrixMock matrixMock;

    private Results results;

    // 存放要查找的数字
    private int number;

    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        //存放每行查找到的次数
        int counter;
        System.out.printf("进程：%s 的查找范围是从 %d 到 %d \n", Thread.currentThread().getName(), firstRow, lastRow);

        for (int i = firstRow; i < lastRow; i++) {
            int row[] = matrixMock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s 线程已经结束", Thread.currentThread().getName());

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
