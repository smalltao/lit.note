package thread.concurrent.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     2017/6/22 18:36
 */
public class Main {

    public static void main(String[] args) {

        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2, exchanger);

        Thread procucerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        procucerThread.start();
        consumerThread.start();

    }
}
