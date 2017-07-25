package com.serviceImpl;

import com.Utils.StringUtils;
import com.service.ThreadService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title:      线程服务. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/7/25 15:47
 */
@Service("threadService")
public class ThreadServiceImpl implements ThreadService {

    private final Logger logger = Logger.getLogger("litThread");
    /**
     * 线程池配置文件对象
     */
    private Properties properties;
    /**
     * 线程池map
     * key: 线程池的名字
     * value: 线程池
     */
    private Map<String, ExecutorService> serviceMap = null;

    /**
     * 默认线程池名称
     */
    private static final String POOL_KEY_DEFAULT = "default";

    /**
     * 默认线程池中的线程数量
     */
    private static final int POOL_KEY_DEFAULT_SIZE = 200;

    @PostConstruct
    public void doInit() throws Exception {
        logger.info("Init threadService start ...");
        serviceMap = new HashMap<>();
        properties = new Properties();
        InputStream url = this.getClass().getClassLoader().getResourceAsStream("thread-pool.properties");
        properties.load(url);

        Map<String, Integer> map = StringUtils.getMap(properties.getProperty("pool_size"));
        map.forEach((key, value) -> {
            if (value != null && value > 0) {
                logger.debug("ThreadService ExecutorService name:[" + key + "],size:[" + value + "]");
                serviceMap.put(key, Executors.newFixedThreadPool(value));
            }
        });
        if (!serviceMap.containsKey(POOL_KEY_DEFAULT)) {
            serviceMap.put(POOL_KEY_DEFAULT, Executors.newFixedThreadPool(POOL_KEY_DEFAULT_SIZE));
        }
        logger.info("Init threadService end ...");
    }

    @PreDestroy
    public void doDestroy() throws Exception {

    }

    public Map<String, ExecutorService> getServiceMap() {
        return serviceMap;
    }
}
