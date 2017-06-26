package thread.executor.done;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      主类. </p>
 * <p>Description 任务执行之后的后续操作</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:11
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        ResultTask resultTask[] = new ResultTask[5];

        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("task " + i);
            resultTask[i] = new ResultTask(executableTask);
            executor.submit(resultTask[i]);
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < resultTask.length; i++) {
            resultTask[i].cancel(true);
        }

        //循环输出未被取消的任务
        for (int i = 0; i < resultTask.length; i++) {
            try {
                if (!resultTask[i].isCancelled()) {
                    System.out.printf("%s : \n", resultTask[i].get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

    }
}
