package com.serviceImpl;

import com.Utils.StringUtils;
import com.service.CacheService;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * <p>Title:      memcached缓存服务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/7/25 16:53
 */
@Service("memcachedService")
public class MemcachedServiceImpl implements CacheService {

    /**
     * memcached 缓存配置文件
     */
    private Properties properties;

    /**
     * Memcached的缓存实现
     */
    protected final Logger logger = Logger.getLogger(getClass());

    private XMemcachedClient memcachedClient;

    @Override
    public void initCache(String cacheName) {
        if (memcachedClient != null) {
            return;
        }
        try {
            properties = new Properties();
            URL url = ClassLoader.getSystemResource("memcached-conf.properties");
            properties.load(url.openStream());

            String memcachedServer = properties.getProperty("memcached_server");
            if (StringUtils.isEmpty(memcachedServer)) {
                logger.error("can't get memcached server address");
                return;
            }
            String[] memcachedServers = StringUtils.split(memcachedServer, ",");

            XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcachedServers[0]));
            memcachedClient = (XMemcachedClient) builder.build();
            if (memcachedServers.length > 1) {
                for (int i = 1; i < memcachedServers.length; i++) {
                    try {
                        memcachedClient.addServer(memcachedServers[i].trim());
                    } catch (Exception ex) {
                        logger.error("can't connect " + memcachedServers[i].trim(), ex);
                    }
                }
            }
        } catch (IOException ex) {
            logger.error("can't conect memcached server", ex);
        }
    }

    @Override
    public Object get(Object key) {
        try {
            return memcachedClient.get(key.toString());
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            logger.error("get cache from memcached error", e);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        try {
            memcachedClient.set(key.toString(), 0, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            logger.error("put value to memcached error", e);
        }
    }

    @Override
    public void put(Object key, Object value, int exp) {
        try {
            memcachedClient.set(key.toString(), exp, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            logger.error("put value to memcached error", e);
        }
    }

    @Override
    public void remove(Object key) {
        try {
            memcachedClient.delete(key.toString());
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            logger.error("remove cache from memcached error", e);
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
