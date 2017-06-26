package com.thread.executor.schedule;

import java.util.Date;

/**
 * <p>Title:      任务类. </p>
 * <p>Description 周期执行线程 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:50
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s 开始于 %s \n", name, new Date());
    }
}
