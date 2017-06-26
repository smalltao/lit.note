package executor.docancel;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      任务类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 11:41
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
            System.out.printf("Task: 任务休眠：%s 秒 \n", duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("任务执行结束： \n");
    }

    @Override
    public String toString() {
        return name;
    }
}
