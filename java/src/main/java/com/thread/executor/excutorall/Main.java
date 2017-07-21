package com.thread.executor.excutorall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>Title:      主类. </p>
 * <p>Description 处理所有任务</p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:11
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Task task = new Task(String.valueOf(i));
            taskList.add(task);
        }

        List<Future<Result>> resultList = null;

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("main: 打印结果");

        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);

            try {
                Result result = future.get();
                System.out.printf("main: %s : %d \n", result.getName(), result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
