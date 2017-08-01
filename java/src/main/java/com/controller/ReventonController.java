package com.controller;

import com.bean.HttpResult;
import com.service.HttpService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Title:      控制器. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/28 11:41
 */
@Controller
public class ReventonController extends CommonController {

    @Autowired
    private HttpService httpService;

    @RequestMapping(value = {"", "/", "/index"})
    public HttpResult index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpResult result = new HttpResult();

        result.setCode("123456");
        result.setMsg("你好，你知道吗？");
        ArrayList<Object> li = new ArrayList<>();
        li.add("我去");
        li.add("修改一下");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        result.setList(li);
        result.setMap(map);

        return result;
    }

    @RequestMapping("/{path}")
    public HttpResult doHttp(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws Exception {
        HttpResult result = new HttpResult();
        httpService.execute();
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
