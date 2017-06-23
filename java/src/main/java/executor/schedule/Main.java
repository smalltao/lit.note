package executor.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      主类. </p>
 * <p>Description 周期性执行任务</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:11
 */
public class Main {

    public static void main(String[] args) {

        //参数1 表示创建一个执行器
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("main : 开始于 ： %s \n", new Date());

        Task task = new Task("Task");
        // 将任务发送到执行器 任务 任务第一次执行后的延迟时间 周期（指两次任务开始的时间间隔） 单位
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.printf("main： 延迟 %s \n", result.getDelay(TimeUnit.MICROSECONDS)); //当前任务到下次一次任务所需等待的剩余时间
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("main： 结束：%s \n", new Date());
    }
}
