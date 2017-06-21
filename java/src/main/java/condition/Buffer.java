package condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title:      数据缓冲类. </p>
 * <p>Description 被生产者和消费者共享 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 11:49
 */
public class Buffer {

    //存放共享数据
    private LinkedList<String> buffer;
    //buffer最大长度
    private int maxSize;
    //用来对功效数据做控制
    private ReentrantLock lock;
    //线程等待插入数据的条件
    private Condition lines;
    // 线程等待空行的条件
    private Condition space;
    // 缓冲区中是否还有数据
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.buffer = new LinkedList<>();
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.lines = lock.newCondition();
        this.space = lock.newCondition();
        this.pendingLines = true;
    }

    /**
     * <p>Title:      向缓冲区中插入数据. </p>
     * <p>Description </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/21 12:08
     */
    public void insert(String line) {
        lock.lock();

        try {
            if (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: 插入行：%d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * <p>Title:      获取缓冲区的第一行数据. </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/21 12:22
     */
    public String get() {
        String line = null;
        lock.lock();
        try {
            // 这里为什么使用while
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: 读取行：缓冲区大小： %d: \n" ,Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return line;
    }

    /**
     * <p>Title:      返回数据行的状态. </p>
     * <p>Description true：有下一行，false：没有下一行</p>
     *
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/21 12:21
     */
    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

}
