package executor.fixedthreadpool;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      线程任务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 11:53
 */
public class Task implements Runnable {

    private String name;

    private Date initDate;

    public Task(String name) {
        this.name = name;
        this.initDate = new Date();
    }

    @Override
    public void run() {
        System.out.printf("%s 任务 %s 创建于 %s \n", Thread.currentThread().getName(), name, initDate);

        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
            System.out.printf("%s 任务 %s 休眠 %s 毫秒 \n ", Thread.currentThread().getName(), name, duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s 任务 %s 结束于 %s \n", Thread.currentThread().getName(), name, initDate);
    }
}
