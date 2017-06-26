package thread.concurrent.cyclicbarrier;

import java.util.Random;

/**
 * <p>Title:      矩阵类. </p>
 * <p>Description 在数字矩阵中寻找一个数字（使用分治炼编程技术）这个矩阵会被分成多个子集，然后每个线程在一个子集中查找，一旦所有线程都完成查找，最终的任务将统一结果</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 11:20
 */
public class MatrixMock {

    private int data[][];

    /**
     * <p>Title:      初始化矩阵对象. </p>
     * <p>Description 用来生成1-10的随机矩阵</p>
     *
     * @param size 矩阵的行数
     * @param size 每行的长度
     * @param size 要寻找的数字
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/22 11:33
     */
    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }
        System.out.printf("矩阵中共有需要查找的字符 %d 个 \n", counter);
    }

    /**
     * <p>Title:      获取指定行的数据. </p>
     * <p>Description </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/22 11:47
     */
    public int[] getRow(int row) {
        if ((row>=0) && (row<data.length)) {
            return data[row];
        }
        return null;
    }


}
