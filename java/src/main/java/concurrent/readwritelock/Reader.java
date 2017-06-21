package concurrent.readwritelock;

/**
 * <p>Title:      读取价格信息类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:46
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        //循环读取两个价格10次
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: 价格1：%f\n", Thread.currentThread().getName(), pricesInfo.getPrices1());
            System.out.printf("%s: 价格2：%f\n", Thread.currentThread().getName(), pricesInfo.getPrices2());
        }

    }
}
