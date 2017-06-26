package thread.concurrent.readwritelock;

/**
 * <p>Title:      价格写入类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:56
 */
public class Writer implements Runnable {

    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        //循环修改价格三次，每次修改之后休眠两秒
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s: 尝试修改价格 \n", Thread.currentThread().getName());
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("%s: 修改成功", Thread.currentThread().getName());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
