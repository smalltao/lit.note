package com.controller;

import com.bean.HttpResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>Title:      com.memcached 缓存控制器. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 14:14
 */
@Controller
@RequestMapping("/memcached")
public class MemcachedController extends CommonController {

    @RequestMapping(value = {"", "/", "/index"})
    public HttpResult index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpResult result = new HttpResult();
        ArrayList<String> list = new ArrayList<>();
        list.add("你好");
        list.add("我是");
        result.setObj(list);
        return result;
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
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        String message = exception.getMessage();
        if (StringUtils.isBlank(message)) {
            message = exception.toString();
            exception.printStackTrace();
        }
        System.out.printf("发生异常了：【 %s 】 \n", message);
    }
}
