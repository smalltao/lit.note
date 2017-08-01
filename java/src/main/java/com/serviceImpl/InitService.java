package com.serviceImpl;

import com.service.HttpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Properties;

@Service("initService")
public class InitService {

    private final Logger logger = Logger.getLogger(InitService.class);

    @Autowired
    private HttpService httpService = null;

    public InitService() {
        //for spring init
    }

    @PostConstruct
    public void doInit() {
        try {
            logger.info("Init service start ...");
            long start = System.currentTimeMillis();

            HashMap<String, Properties> propertiesHashMap = new HashMap<>();
            Properties properties = PropertiesLoaderUtils.loadAllProperties("controller-test.properties", this.getClass().getClassLoader());
            propertiesHashMap.put("test", properties);

            httpService.doInit(propertiesHashMap);
            long end = System.currentTimeMillis();
            logger.info("Init service end ..., cost: [" + (end - start) + "]");
        } catch (Exception e) {
            logger.info("Init service error, mssage: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void doDestroy() {
        try {
            httpService.doDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
