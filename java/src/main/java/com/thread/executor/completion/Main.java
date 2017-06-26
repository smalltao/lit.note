package com.thread.executor.completion;

import java.util.concurrent.*;

/**
 * <p>Title:      主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 10:15
 */
public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        //发送请求与执行结果分离的执行器服务
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        ReportRequest faceReportRequest = new ReportRequest("face", service);
        ReportRequest onlineReportRequest = new ReportRequest("online", service);

        Thread faceThread = new Thread(faceReportRequest);
        Thread onlineThread = new Thread(onlineReportRequest);

        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        System.out.printf("开发执行线程 \n");

        faceThread.start();
        onlineThread.start();
        senderThread.start();

        try {
            System.out.printf("main： 等待上报请求结束 \n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("main: 关闭执行器： \n");
        executor.shutdown();

        //等待所有的方法执行结束
        try {

            executor.awaitTermination(1, TimeUnit.DAYS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //结束 ReportProcessor的执行
        processor.setEnd(true);

        System.out.printf("main： ends");
    }
}
