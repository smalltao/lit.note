package com.thread.executor.excutorfirst;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title:      用户身份验证主类. </p>
 * <p>Description 同时多个任务，处理第一个返回结果的场景：比如同时抓取多个接口，以先返回的数据为准 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:11
 */
public class Main {

    public static void main(String[] args) {

        String username = "test";
        String password = "password";

        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("database");

        TaskValidator ladpTask = new TaskValidator(ldapValidator, username, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ladpTask);
        taskList.add(dbTask);


        ExecutorService executor = Executors.newCachedThreadPool();
        String result;

        try {
            result = executor.invokeAny(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("main: 执行结束");
    }
}
