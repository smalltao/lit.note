package thread.executor.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 13:18
 */
public class Main {
    public static void main(String[] args) {


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);

            FactorialCalculator factorialCalculator = new FactorialCalculator(number);

            Future<Integer> result = executor.submit(factorialCalculator);

            resultList.add(result);
        }

        //监控执行器运行状态
        do {
            System.out.printf("main： 执行器中完成的任务数: %s  \n", executor.getCompletedTaskCount());

            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("main: 任务 %d 执行状态：%s \n", i, result.isDone());

                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        //循环输出任务得到的结果
        System.out.printf("任务的结果为： \n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            //获取执行结束得到的结果
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("main: 任务 %d 结果为：%s \n", i, number);

        }
        executor.shutdown();
    }
}
