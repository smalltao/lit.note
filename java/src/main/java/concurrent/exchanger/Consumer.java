package concurrent.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>Title:      消费者. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 18:24
 */
public class Consumer implements Runnable {

    private List<String> buffer;

    private Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //循环10次交换数据
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("消费者，第 %s 次循环 \n", cycle);

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("消费者，缓冲区大小 %s \n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("消费者，消费的串为 %s \n", message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
