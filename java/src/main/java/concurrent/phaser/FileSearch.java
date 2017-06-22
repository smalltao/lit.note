package concurrent.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      文件查找类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 14:06
 */
public class FileSearch implements Runnable {


    //存储查找到的文件夹
    private String initPath;

    //存储查找到的文件的后缀名
    private String end;

    //存储查找到的文件的完整路径
    private List<String> results;

    // 控制不同阶段的同步
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }

    private void directoryProcess(File file) {
        File list[] = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * <p>Title:      过滤每个阶段的结果. </p>
     * <p>Description </p>
     *
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/22 14:22
     */
    private void filterResult() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();

        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            if (actualDate - fileDate < TimeUnit.MICROSECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
        }
        results = newResults;
    }

    // 检查结果，在第一阶段和第二阶段结束时被调用
    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("线程 %s phase %d 0 条结果\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("线程 %s phase %d 结束\n", Thread.currentThread().getName(), phaser.getPhase());
            //通知phaser当前线程已经结束，不执行接下来的阶段操作
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("%s phase %d %d 条结果 \n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            //通知phaser 当前线程已经完成当前阶段，需要被阻塞知道其他线程都完成当前阶段
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    //将结果集打印到控制台
    private void showInfo() {
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("线程 %s 的路径 %s \n", Thread.currentThread().getName(),file.getAbsoluteFile());
        }
        phaser.arriveAndAwaitAdvance();
    }


    @Override
    public void run() {
        //使查找工作在所有线程创建之后在开始
        phaser.arriveAndAwaitAdvance();

        System.out.printf("线程 %s 开始。。。", Thread.currentThread().getName());

        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }

        //结果集为空时返回
        if (!checkResults()) {
            return;
        }

        fileProcess(file);

        if (!checkResults()) {
            return;
        }

        showInfo();

        phaser.arriveAndDeregister();
        System.out.printf("线程 %s 完成", Thread.currentThread().getName());
    }

}
