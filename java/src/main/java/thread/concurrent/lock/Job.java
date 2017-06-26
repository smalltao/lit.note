package thread.concurrent.lock;

/**
 * <p>Title:      工作类. </p>
 * <p>Description 工作类 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     2017/6/21 10:06
 */
public class Job implements Runnable{

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }


    @Override
    public void run() {
        System.out.printf("%s: 输出一个文档开始\n", Thread.currentThread().getName());
        printQueue.printJoib(new Object());
        System.out.printf("%s: 输出一个文档结束\n", Thread.currentThread().getName());
    }
}
