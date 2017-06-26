package com.controller;

import com.service.InterceptorService;
import com.whalin.MemCached.MemCachedClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title:      公共控制器. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/26 14:16
 */
public class CommonController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MemCachedClient memCachedClient;

    @Autowired
    private InterceptorService interceptorService;

    protected String viewPath = "default/index";

}
