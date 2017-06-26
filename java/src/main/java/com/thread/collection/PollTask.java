package com.thread.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * <p>Title:      poll的意思是从队尾取得并移除. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/25 0025 21:51
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {

        //循环5000次，每次从列表中取出两个元素
        for (int i = 0; i < 5000; i++) {
            // 返回并移除列表的第一个元素
            list.pollFirst();
            // 返回并移除列表的最后一个元素
            list.pollLast();
        }
    }
}
