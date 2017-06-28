package com.service;

import org.apache.http.HttpEntity;

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
    public void doInit() throws Exception;

    /**
     * <p>Title:      销毁链接池. </p>
     * <p>Description </p>
     *
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/28 16:39
     */
    public void doDestroy() throws Exception;

    /**
     * <p>Title:      执行请求. </p>
     * <p>
     * 1. 请求的执行全部采用流式，不占用内存
     * 2. 请求采用HttpEntity 请求体
     * 3. 只进行请求的发送和接收工作
     * 4. 其他检测放到上层 ，比如 ： url检测 、header设置、cookies设置、refer过滤
     * </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/28 16:58
     */
    public HttpEntity doRequest(HttpEntity entity) throws Exception;


}
