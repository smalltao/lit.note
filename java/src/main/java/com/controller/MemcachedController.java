package com.controller;

import com.bean.Texts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Title:      com.memcached 缓存控制器. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 14:14
 */
@Controller
@RequestMapping("/memcached/index")
public class MemcachedController extends CommonController {

    @RequestMapping(value = {"", "/", "/index"})
    public Texts index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Texts texts = new Texts();
        ArrayList<String> list = new ArrayList<>();
        list.add("你好");
        list.add("我是");
        texts.setText(list);

        return texts;
    }

    @RequestMapping(value = "list")
    public HashMap list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap resultMap = new HashMap();
        ArrayList<String> index1 = new ArrayList<>();
        ArrayList<String> index2 = new ArrayList<>();
        resultMap.put("index1", index1);
        resultMap.put("index2", index2);
        System.out.printf("处理。。。\n");

        return resultMap;
    }

    /**
     * <p>Title:      统一异常处理. </p>
     * <p>Description </p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/26 16:15
     */
//    @ExceptionHandler(Exception.class)
//    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
//        String message = exception.getMessage();
//        if (StringUtils.isBlank(message)) {
//            message = exception.toString();
//            exception.printStackTrace();
//        }
//        System.out.printf("发生异常了：【 %s 】 \n", message);
//    }
}
