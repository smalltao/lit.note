package com.thread.concurrent.condition;

/**
 * <p>Title:      文本文件模拟类. </p>
 * <p>Description 在锁中使用多条件，Condition提供挂起和唤醒线程的机制 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/21 11:25
 */
public class FileMock {

    //文件内容
    private String content[];

    //从文件读取内容的行号
    private int index;

    //使用字符初始化文件内容
    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) Math.random() * 255;
                builder.append((char) indice);
            }
            content[i] = builder.toString();
        }
        index = 0;
    }

    /**
     * <p>Title:      是否有下一行 </p>
     *
     * @return 有下一行返回true，没有下一行返回false.
     * @Author <a href="litaoos2862@sogou-inc.com"/>litaoos2862</a>
     * @CreateDate 2017/6/21 11:38
     */
    public boolean hasMoreLines() {
        return index < content.length;
    }

    /**
     * <p>Title:      获取行内容. </p>
     * <p>Description 获取index指定行的内容，并index加1 </p>
     *
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>litaoos2862</a>
     * @CreateDate 2017/6/21 11:42
     */
    public String getLine() {
        if (hasMoreLines()) {
            System.out.println("剩余行数mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}
