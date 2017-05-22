package com.sogou.reventon.controller;

import com.sogou.reventon.condition.TranslateCollectionCondition;
import com.sogou.reventon.service.MicrosoftService;
import com.sogou.reventon.util.AESEncryptUtil;
import com.sogou.reventon.util.Constant;
import net.sf.json.JSONObject;
import org.apache.xerces.impl.dv.util.Base64;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author litaoos2862
 * @date 2017.05.15
 * @description 基本控制器服务层测试 未使用mock
 */
public class BaseControllerTest {

    private final static String beanConfig = "applicationServlet.xml";

    private static BeanFactory factory;

    private static MicrosoftService microsoftService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ApplicationContext aContext = new ClassPathXmlApplicationContext(beanConfig);
        factory = aContext;
        microsoftService = (MicrosoftService) factory.getBean("microsoftService");
    }

    /**
     * 单用户 上传测试 （单条数据）
     */
    @Test
    public void testOneReportCollection() throws Exception {
        String jsonData = "{\"collections\":[ {\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"单条\", \"postport\":\"731334268@qq.com\", \"dic\":\"It's time to prepare the resume \", \"status\":1, \"collectionTime\":\"1494919700000\" }] }";
        JSONObject json = JSONObject.fromObject(jsonData);
        String result = microsoftService.reportCollection(json);
        System.out.println("单条数据上传结果：【" + result + "】");
    }

    /**
     * 单用户 上传测试 （多条数据）
     */
    @Test
    public void testMultipleReportCollection() throws Exception {
        String jsonData = "{\"collections\":[ {\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"多条1\", \"postport\":\"731334268@qq.com\", \"dic\":\"It's time to prepare the resume \", \"status\":1,\"collectionTime\":\"1494919700000\" },{\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"多条2\", \"postport\":\"731334268@qq.com\", \"dic\":\"更新下\", \"status\":1 ,\"collectionTime\":\"1494919700000\"}] }";
        JSONObject json = JSONObject.fromObject(jsonData);
        String result = microsoftService.reportCollection(json);
        System.out.println("多条数据上传结果：【" + result + "】");
    }

    /**
     * 完整性测试
     */
    @Test
    public void testTransactionalReportCollection() throws Exception {
        String jsonData = "{\"collections\":[ {\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"多条9\", \"postport\":\"731334268@qq.com\", \"dic\":\"It's time to prepare the resume \", \"status\":1 },{\"transFrom\":\"\", \"transTo\":\"en\", \"text\":\"多条10\", \"postport\":\"731334268@qq.com\", \"dic\":\"更新下\", \"status\":1,\"collectionTime\":\"1494919700000\"}] }";
        exception.expect(Exception.class);
        exception.expectMessage("源语言不能为空");
        JSONObject json = JSONObject.fromObject(jsonData);
        microsoftService.reportCollection(json);
    }

    /**
     * 单用户 删除测试
     */
    @Test
    public void testDeleteReportCollection() throws Exception {
        String jsonData = "{\"collections\":[ {\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"单条\", \"postport\":\"731334268@qq.com\", \"dic\":\"It's time to prepare the resume \", \"status\":0 ,\"collectionTime\":\"1494919700000\"}] }";
        JSONObject json = JSONObject.fromObject(jsonData);
        String result = microsoftService.reportCollection(json);
        System.out.println("删除结果：【" + result + "】");
    }

    /**
     * 获取收藏结果
     */
    @Test
    public void testListCollection() throws Exception {

        TranslateCollectionCondition condition = new TranslateCollectionCondition();
        condition.setPostport("731334268@qq.com");

        String result = microsoftService.listTranslateCollectionByCondition(condition, 1, Constant.COLLECTION_PAGE_SIZE);
        System.out.println("收藏结果列表：【" + result + "】");
    }

    @Test
    public void testEncrypt() throws Exception {
        String str = "{\"collections\":[ {\"transFrom\":\"zh-CHS\", \"transTo\":\"en\", \"text\":\"单条\", \"postport\":\"731334268@qq.com\", \"dic\":\"It's time to prepare the resume \", \"status\":1, \"collectionTime\":\"1494919700000\" }] }";
        System.out.println("原文：" + str);

        //初始化密钥
        byte[] key;
        try {
//            key = AESEncryptUtil.getKey(Constant.REPORT_COLLECTION_KEY, 128);
            key = AESEncryptUtil.getKey(Constant.LIST_COLLECTION_KEY, 128);
            System.out.print("密钥：");
            for (int i = 0; i < key.length; i++) {
                System.out.printf("%x", key[i]);
            }
            System.out.print("\n");
            System.out.println("16进制：" + toHexString1(key));
            //加密数据
            byte[] data = AESEncryptUtil.encrypt(str.getBytes(), key);
            System.out.print("加密后：");
            for (int i = 0; i < data.length; i++) {
                System.out.printf("%x", data[i]);
            }
            System.out.print("\n");

            //解密数据
            data = AESEncryptUtil.decrypt(data, key);
            System.out.println("解密后：" + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuildListCollectionData() throws Exception {
        String str = "{\"postport\":\"731334268@qq.com\"}";
        System.out.println("原文：" + str);

        //初始化密钥
        byte[] key;
        try {
            key = AESEncryptUtil.getKey(Constant.LIST_COLLECTION_KEY, 128);
            System.out.print("密钥：");
            for (int i = 0; i < key.length; i++) {
                System.out.printf("%x", key[i]);
            }
            System.out.print("\n");
            //加密数据
            byte[] data = AESEncryptUtil.encrypt(str.getBytes(), key);
            System.out.print("加密后：");
            for (int i = 0; i < data.length; i++) {
                System.out.printf("%x", data[i]);
            }
            System.out.print("\n");

            String content1 = Base64.encode(data);
            System.out.println("加密后结果：" + content1);


            //解密数据
            data = AESEncryptUtil.decrypt(data, key);
            System.out.println("解密后：" + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEncryptString() throws Exception {
        String key = AESEncryptUtil.getKeyString(Constant.LIST_COLLECTION_KEY, 128);

        String encData = "{\"postport\":\"731334268@qq.com\"}";
        encData = AESEncryptUtil.encrypt(encData, key);
        System.out.println("密文：" + URLEncoder.encode(encData, "utf-8"));
        String srcStr = AESEncryptUtil.decrypt(encData, key);
        System.out.println("原文：" + URLDecoder.decode(srcStr, "utf-8"));
    }

    public String toHexString1(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }
}
