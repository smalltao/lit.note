package condition;

/**
 * <p>Title:      生产者线程对象. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 13:43
 */
public class Producer implements Runnable {

    private FileMock mock;

    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // 读取filemock中所有行并插入读到的数据到缓冲区
        buffer.setPendingLines(true);
        while(mock.hasMoreLines()) {
            String line = mock.getLine();
            buffer.insert(line);

        }
        buffer.setPendingLines(false);
    }
}
