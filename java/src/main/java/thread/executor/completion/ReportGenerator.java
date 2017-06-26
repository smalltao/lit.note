package thread.executor.completion;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      请求创造者. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 17:30
 */
public class ReportGenerator implements Callable<String> {

    //表示发送的数据
    private String sender;

    private String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {

        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
            System.out.printf("%s_%s 创建一个上报信息用了 %d 秒", sender, title,duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ret = sender + ": " + title;
        return ret;
    }

}
