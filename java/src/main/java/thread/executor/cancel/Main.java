package thread.executor.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();

        System.out.println("main： 开始。。。");
        Future<String> future = executor.submit(task);

        //休眠200毫秒
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("取消任务。。。");
        //true表示任务已经开始了的话也取消
        future.cancel(true);

        System.out.printf("main： 是否被取消：%s \n", future.isCancelled());
        System.out.printf("main： 是否被结束：%s \n", future.isDone());

        System.out.printf("结束线程执行器 \n");
        executor.shutdown();

    }
}
