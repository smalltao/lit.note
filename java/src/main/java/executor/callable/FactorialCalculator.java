package executor.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      阶乘结束. [fæk'tɔːrɪəl]Factorial </p>
 * <p>Description 在执行器中执行任务并返回结果 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 12:31
 */
public class FactorialCalculator implements Callable<Integer> {

    //存放阶乘计算结果
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {

        int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 0; i < number; i++) {
                result *= i;
                TimeUnit.SECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d \n", Thread.currentThread().getName(),result);
        return result;
    }
}
