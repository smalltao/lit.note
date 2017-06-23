package executor.done;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * <p>Title:      结果处理任务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 16:48
 */
public class ResultTask extends FutureTask<String> {

    private String name;

    public ResultTask(Callable<String> callable) {
        super(callable);
        name = ((ExecutableTask) callable).getName();
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("任务：%s 已经被取消 \n", name);
        } else {
            System.out.printf("任务：%s 已经完成 \n", name);
        }
    }
}
