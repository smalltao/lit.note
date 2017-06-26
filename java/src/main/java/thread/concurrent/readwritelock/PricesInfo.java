package thread.concurrent.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>Title:      价格信息类. </p>
 * <p>Description 使用读写锁实现同步数据访问 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 10:38
 */
public class PricesInfo {

    private double prices1;

    private double prices2;

    private ReadWriteLock lock;

    public PricesInfo() {
        prices1 = 1.0;
        prices2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrices1() {
        lock.readLock().lock();
        double value = prices1;
        lock.readLock().unlock();
        return prices1;
    }

    public double getPrices2() {
        lock.readLock().lock();
        double value = prices2;
        lock.readLock().unlock();
        return prices2;
    }

    public void setPrices(double prices1, double prices2) {
        lock.writeLock().lock();
        this.prices1 = prices1;
        this.prices2 = prices2;
        lock.writeLock().unlock();
    }
}
