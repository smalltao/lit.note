package com.thread.executor.done;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      在控制器中控制任务的完成. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 16:37
 */
public class ExecutableTask implements Callable<String> {

    private String name;

    public ExecutableTask(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {

        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "你好，我的名字是：" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
