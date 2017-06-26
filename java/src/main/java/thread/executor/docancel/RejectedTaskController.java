package thread.executor.docancel;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:      任务被拒绝处理类，需要继承RejectedExectionHandler. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 11:21
 */
public class RejectedTaskController implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: 这个任务已经被拒绝了： %s \n", r.toString());

        System.out.printf("RejectedTaskController: 执行器：%s \n", executor.toString());

        System.out.printf("RejectedTaskController: 状态：%s \n", executor.isTerminating());

        System.out.printf("RejectedTaskController: 是否结束：%s \n", executor.isTerminated());
    }


}
