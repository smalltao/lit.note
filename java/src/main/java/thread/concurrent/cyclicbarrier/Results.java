package thread.concurrent.cyclicbarrier;


/**
 * <p>Title:      保存矩阵中每行找到指定数字的次数. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 12:01
 */
public class Results {

    private int[] data;

    public Results(int size) {
        this.data = new int[size];
    }

    public int[] getData() {
        return data;
    }


    public void setData(int i, int counter) {
        data[i] = counter;
    }
}
