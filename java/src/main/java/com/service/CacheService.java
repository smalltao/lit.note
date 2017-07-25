package com.service;

/**
 * <p>Title:      缓冲服务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/7/25 16:46
 */
public interface CacheService<T> {
    /**
     * 初始化缓存对象
     *
     * @param cacheName 缓存对象名称
     */
    public void initCache(String cacheName);

    /**
     * 获取缓存对象
     *
     * @param key 缓存的key
     * @return
     */
    public T get(Object key);

    /**
     * 设置缓存对象
     *
     * @param key   缓存的key
     * @param value 对象
     */
    public void put(Object key, T value);

    /**
     * 向缓冲写入内容
     *
     * @param key   缓存的key
     * @param value 对象
     * @param exp   超时时间
     */
    public void put(Object key, T value, int exp);

    /**
     * 移除缓存
     *
     * @param key 缓存的key
     */
    public void remove(Object key);

    /**
     * 清空缓存
     */
    public void clear();
}
