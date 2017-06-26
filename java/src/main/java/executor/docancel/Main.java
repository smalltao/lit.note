package executor.docancel;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:      当发送任务到执行器中的时候被拒绝怎么处理. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 11:01
 */
public class Main {

    public static void main(String[] args) {
        //被拒绝控制器
        RejectedTaskController cotroller = new RejectedTaskController();

        //被拒绝执行器
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // 设置任务被拒绝的处理程序
        executor.setRejectedExecutionHandler(cotroller);


        System.out.printf("main：开始 ... \n");
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task" + i);
            executor.submit(task);
        }

        System.out.printf("main: 关闭执行器 ... \n");
        executor.shutdown();

        //创建另一个任务并发送给执行器
        System.out.printf("main: send another task \n");

        Task task = new Task("RejectedTask");
        executor.submit(task);

        System.out.printf("任务结束 \n");

    }

}
