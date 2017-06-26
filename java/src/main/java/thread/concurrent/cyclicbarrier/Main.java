package thread.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 12:29
 */
public class Main {

    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINS_PARTICIPANTS = 2000;

        MatrixMock matrixMock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        Grouper grouper = new Grouper(results);

        // cyclicBarrier将等待5个线程结束后执行grouper对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, grouper);

        Searcher searcher[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searcher[i] = new Searcher(i * LINS_PARTICIPANTS,(i*LINS_PARTICIPANTS)+LINS_PARTICIPANTS,matrixMock,results,5,cyclicBarrier);
            Thread thread = new Thread(searcher[i]);
            thread.start();
        }
    }
}
