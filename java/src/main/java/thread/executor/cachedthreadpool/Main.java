package thread.executor.cachedthreadpool;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 12:12
 */
public class Main {

    public static void main(String[] args) {

        //使用线程池执行100 次任务
        Service service = new Service();
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            service.executeTask(task);
        }
        service.endServer();
    }
}
