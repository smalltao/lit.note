package com.thread.concurrent.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>Title:      生产者. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 18:05
 */
public class Producer implements Runnable {

    private List<String> buffer;

    private Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //循环10次数据交换
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("生产者, 第 %s 次循环 \n", cycle);
            //每次循环添加10个字符串到buffer中
            for (int j = 0; j < 10; j++) {
                String message = "Event " + (i * 10 + j);
                System.out.printf("生产者, 产生的串：%s \n" , message);
                buffer.add(message);
            }
            // 使用 exchanger的exchange方法与生产者交换数据
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("生产者，缓冲区大小：%s \n", buffer.size());
            cycle++;
        }

    }
}
