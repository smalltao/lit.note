package com.service;

import java.util.HashMap;
import java.util.Properties;

/**
 * <p>Title:      http 接口服务. </p>
 * <p>
 * 1. 随系统启动一个默认的链接池
 * 2. 可以查看这个默认链接池的各种状态
 * <p>
 * 使用线程池套链接池 把请求放置到线程里 当请求过大时，请求还在线程里并没有得到链接，超时时自动结束
 * 单独使用链接池时，请求都在链接池里，链接池设置了上线的话，请求也不会执行，超时自动结束
 * </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/28 15:22
 */
public interface HttpService {

    /**
     * <p>Title:      初始化默认链接池参数. </p>
     * <p>Description </p>
     *
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/28 15:42
     */
    public void doInit(HashMap<String, Properties> propertiesHashMap) throws Exception;

    /**
     * <p>Title:      销毁链接池. </p>
     * <p>Description </p>
     *
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/28 16:39
     */
    public void doDestroy() throws Exception;

    /**
     * 执行请求
     *
     * @throws Exception
     */
    public void execute() throws Exception;
}
