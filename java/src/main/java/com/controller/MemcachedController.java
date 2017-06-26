package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        return "memcached/index";
    }

}
