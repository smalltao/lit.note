package concurrent.readwritelock;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 11:02
 */
public class Main {



    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();
        //创建5个读线程，1个写线程
        Reader reader[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];

        for (int i = 0; i < 5; i++) {
            reader[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(reader[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread writeThread = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        writeThread.start();
    }

}
