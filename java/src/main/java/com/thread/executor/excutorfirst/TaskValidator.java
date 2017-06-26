package com.thread.executor.excutorfirst;

import java.util.concurrent.Callable;

/**
 * <p>Title:      验证任务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 14:04
 */
public class TaskValidator implements Callable<String> {

    private UserValidator userValidator;

    private String name;

    private String password;

    public TaskValidator(UserValidator userValidator, String name, String password) {
        this.userValidator = userValidator;
        this.name = name;
        this.password = password;
    }

    @Override
    public String call() throws Exception {

        if (!userValidator.validate(name, password)) {
            System.out.printf("用户： %s 没有找到，验证失败", userValidator.getName());
            throw new Exception("用户验证失败");
        }
        System.out.printf("用户： %s 已经找到", userValidator.getName());
        return userValidator.getName();
    }

}
