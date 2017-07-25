package com.serviceImpl;

import com.Utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.bean.User;
import com.service.CacheService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p>Title:      redis服务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/7/25 17:18
 */
@Service("redisService")
public class RedisServiceImpl implements CacheService {

    protected final Logger logger = Logger.getLogger(getClass());

    /**
     * redis 缓存配置文件
     */
    private Properties properties;

    private static ShardedJedisPool pool;

    @Override
    public void initCache(String cacheName) {
        if (pool != null) {
            return;
        }

        properties = new Properties();
        URL url = ClassLoader.getSystemResource("cluster-conf.properties");
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String redisServer = properties.getProperty("redis_server");

        if (StringUtils.isEmpty(redisServer)) {
            logger.error("can't get redis server address");
            return;
        }
        String[] redisServers = StringUtils.split(redisServer, ",");

        List<JedisShardInfo> shards = new ArrayList<>();
        for (int i = 0; i < redisServers.length; i++) {
            String[] hostPort = StringUtils.split(redisServers[i].trim(), ":");
            String host = hostPort[0];
            int port = 6379;
            if (hostPort.length == 2) {
                port = NumberUtils.toInt(hostPort[1], 6379);
            }
            try {
                shards.add(new JedisShardInfo(host, port, "redis_" + i));
            } catch (Exception ex) {
                logger.error("can't connect " + redisServers[i].trim(), ex);
            }
        }
        // 构造池
        pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
    }

    @Override
    public Object get(Object key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = pool.getResource();
            byte[] bytes = shardedJedis.get(key.toString().getBytes());
            if (bytes == null || bytes.length == 0)
                return null;

            return JSON.toJSONBytes(bytes);
        } catch (Exception ex) {
            logger.error("get cache from redis error", ex);
        } finally {
            if (shardedJedis != null) {
                pool.returnResource(shardedJedis);
            }
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = pool.getResource();
            shardedJedis.set(key.toString().getBytes(), JSON.toJSONBytes(value));
        } catch (Exception ex) {
            logger.error("put cache to redis error", ex);
        } finally {
            if (shardedJedis != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    @Override
    public void put(Object key, Object value, int exp) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = pool.getResource();
            shardedJedis.setex(key.toString().getBytes(), exp, JSON.toJSONBytes(value));
        } catch (Exception ex) {
            logger.error("put cache to redis error", ex);
        } finally {
            if (shardedJedis != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    @Override
    public void remove(Object key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = pool.getResource();
            shardedJedis.del(key.toString().getBytes());
        } catch (Exception ex) {
            logger.error("remove cache from redis error", ex);
        } finally {
            if (shardedJedis != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        JedisPool pool2 = new JedisPool(new JedisPoolConfig(), "localhost");
        Jedis jedis = null;
        try {
            jedis = pool2.getResource();

            User m = new User();
            m.setName("dawei");

            jedis.set("user".getBytes(), JSON.toJSONBytes(m));

            User m1 = (User) JSON.parse(jedis.get("user".getBytes()));
            System.out.println(m1.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
