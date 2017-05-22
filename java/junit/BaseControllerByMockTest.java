package com.sogou.reventon.controller;

import com.sogou.reventon.bean.QueryRequest;
import com.sogou.reventon.service.MicrosoftService;
import com.sogou.reventon.util.AESEncryptUtil;
import com.sogou.reventon.util.Constant;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

;

/**
 * @author litaoos2862
 * @date 2017.05.19
 * @description 使用mock测试基本控制器
 *
 * TODO 可以考虑接入 memcached缓存切面 zookeeper节点负载
 */
public class BaseControllerByMockTest {

    private MockMvc mockMvc;

    private final static String beanConfig = "applicationServlet.xml";

    private static BeanFactory factory;

    private static MicrosoftService microsoftService;

    private static MvcResult result;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        ApplicationContext aContext = new ClassPathXmlApplicationContext(beanConfig);
        factory = aContext;
        microsoftService = (MicrosoftService) factory.getBean("microsoftService");

        BaseController baseController = new BaseController();

        Field microsoftServiceField = baseController.getClass().getDeclaredField("microsoftService");
        microsoftServiceField.setAccessible(true);
        microsoftServiceField.set(baseController, microsoftService);

        mockMvc = MockMvcBuilders.standaloneSetup(baseController).build();
    }

    @Test
    public void reportFeedBack() throws Exception {

        String jsonData = "{\"appVersion\":\"1.1.1\",\"nginxIp\":\"\",\"content\":\"今天是个好日子，心想的事情都能成\",\"phoneVersion\":\"ipone 10 装逼 plus\",\"imie\":\"一堆数字\",\"uid\":\"uuuuuuuuuuuid\",\"osVersion\":\"os china\",\"passport\":\"\",\"phone\":\"18311444223\",\"clientIp\":\"\",\"email\":\"731334268@qq.com\"}";
        String key = AESEncryptUtil.getKeyString(Constant.FEED_BACK_KEY, 128);

        jsonData = AESEncryptUtil.encrypt(jsonData, key);

        result = mockMvc.perform(MockMvcRequestBuilders.post("/reportFeedBack.jws")
                .param("jsonData", jsonData))
                .andDo(print())
                .andReturn();

    }

    @After
    public void setDown() throws Exception {
        QueryRequest queryRequest = QueryRequest.getOrCreate(result.getRequest(), result.getResponse());

        System.out.println("响应结果：【" + queryRequest.getResult() + "】");
    }
}
