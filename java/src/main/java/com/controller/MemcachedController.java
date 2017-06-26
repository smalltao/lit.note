package com.controller;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@RequestMapping("/memcached")
public class MemcachedController extends CommonController implements HandlerInterceptor {

    @RequestMapping(value = {"", "/", "/index"}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap resultMap = new HashMap();
        resultMap.put("姓名", "李涛");
        resultMap.put("年龄", "27");
        System.out.printf("处理。。。\n");

        return JSONObject.fromObject(resultMap).toString();
    }

    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap resultMap = new HashMap();
        ArrayList<String> index1 = new ArrayList<>();
        ArrayList<String> index2 = new ArrayList<>();
        resultMap.put("index1", index1);
        resultMap.put("index2", index2);

        System.out.printf("处理。。。\n");

        return JSONObject.fromObject(resultMap).toString();
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

    /**
     * <p>Title:      controller 之前. </p>
     * <p>Description 该方法将在Controller处理之前进行调用，可以链式调用，需要链式定义</p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/26 17:23
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        handlerMethod.getBean();
        return true;
    }

    /**
     * <p>Title:      controller 处理之后，DispatcherServlet进行视图的渲染之前. </p>
     * <p>Description 这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用</p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/26 17:27
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.printf("第二步 \n");
    }

    /**
     * <p>Title:      该方法将在整个请求完成之后. </p>
     * <p>Description DispatcherServlet渲染了视图执行</p>
     *
     * @param
     * @return
     * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
     * @CreateDate 2017/6/26 17:29
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.printf("第三步 \n");
    }
}
