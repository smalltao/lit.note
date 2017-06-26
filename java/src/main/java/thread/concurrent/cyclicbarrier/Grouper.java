package thread.concurrent.cyclicbarrier;

/**
 * <p>Title:      计算矩阵中查找到的总次数. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 12:24
 */
public class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        int[] data = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.printf("总共找到的次数是：%d \n" , finalResult);
    }

}
