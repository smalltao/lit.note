package com.thread.executor.cancel;

import java.util.concurrent.Callable;

/**
 * <p>Title:      任务类. </p>
 * <p>Description 取消执行其中任务 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:50
 */
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("线程在运行中。。。\n");
            Thread.sleep(20);
        }
    }
}
