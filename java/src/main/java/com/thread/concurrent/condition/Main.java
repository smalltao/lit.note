package com.thread.concurrent.condition;


/**
 * <p>Title:      主类. </p>
 * <p>Description 生产者 消费者问题 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 13:59
 */
public class Main {

    public static void main(String[] args) {

        // 一个生产者，三个消费者
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(mock, buffer);

        Thread producerThread = new Thread(producer, "Producer");

        Consumer consumer[] = new Consumer[3];
        Thread consumerThread[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumer[i] = new Consumer(buffer);
            consumerThread[i] = new Thread(consumer[i], "consumer" + i);
        }

        producerThread.start();
        for (int i = 0; i < 3; i++) {
            consumerThread[i].start();
        }
    }
}
