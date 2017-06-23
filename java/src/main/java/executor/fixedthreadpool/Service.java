package executor.fixedthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:      创建固定大小的线程执行器，可以解决任务过大时的高负荷. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 12:01
 */
public class Service {

    //线程池执行器
    private ThreadPoolExecutor executor;

    public Service() {
        //使用线程执行器工厂创建, 参数为 池中线程最大数
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task) {
        System.out.println("一个任务已经到达");
        //执行任务
        executor.execute(task);

        System.out.printf("执行器中线程的实际数量：%s \n", executor.getPoolSize());
        System.out.printf("执行器中正在执行的任务的线程数量：%s \n", executor.getActiveCount());
        System.out.printf("线程池中已经发送到执行器上的任务数：%s \n", executor.getTaskCount());
        System.out.printf("线程池完成的线程数：%s \n", executor.getCompletedTaskCount());

    }

    public void endServer() {
        executor.shutdown();
    }

}
