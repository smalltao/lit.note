package thread.executor.completion;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      获取处理过后的结果. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 9:59
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> service;

    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                //获取下一个已经完成的Future 对象
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                //通过Future的get方法获取任务的结果
                if (result != null) {
                    String reprot = result.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("上报结束 \n");
        }
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
