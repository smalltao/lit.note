package thread.executor.delay;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * <p>Title:      任务类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:50
 */
public class Task implements Callable<String> {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s 开始于 %s \n", name, new Date());
        return "你好";
    }
}
