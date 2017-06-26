package thread.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * <p>Title:      增加任务线程. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/25 0025 21:45
 */
public class AddTask implements Runnable {

    // 非阻塞列表
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override

    public void run() {
        //循环10000个字符串放入到列表中
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            //将新元素添加到列表的尾部
            list.add(name + " event " + i);
        }

    }
}
