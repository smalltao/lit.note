package thread.executor.cachedthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:      线程池执行器. </p>
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
        //使用线程执行器工厂创建
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(Task task) {
        System.out.println("一个任务已经到达");
        //执行任务
        executor.execute(task);

        System.out.printf("执行器中线程的实际数量：%s \n", executor.getPoolSize());
        System.out.printf("执行器中正在执行的任务的线程数量：%s \n", executor.getActiveCount());
        System.out.printf("线程池完成的线程数：%s \n", executor.getCompletedTaskCount());

    }

    public void endServer() {
        executor.shutdown();
    }

}
