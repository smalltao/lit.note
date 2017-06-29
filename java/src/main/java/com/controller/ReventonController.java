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
import java.util.HashMap;

/**
 * <p>Title:      数据中心控制器. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/28 11:41
 */
@Controller
@RequestMapping("/reventondc")
public class ReventonController extends CommonController {

    @RequestMapping(value = {"", "/", "/index"})
    public HttpResult index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpResult result = new HttpResult();

        result.setCode("123456");
        result.setMsg("你好，你知道我是谁吗？");
        ArrayList<Object> li = new ArrayList<>();
        li.add("我去");
        li.add("修改一下，看看服务器上的数据是否正常，我也是醉了");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        result.setList(li);
        result.setMap(map);

        return result;
    }

    /**
     * TODO 可以通过 @PathVariable path属性实现一个动态变量控制器
     * 根据不同的path 路由到不同的请求上
     * */


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
