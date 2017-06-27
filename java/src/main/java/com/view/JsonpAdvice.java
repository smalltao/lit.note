package com.view;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * <p>Title:      spring 4.1以后使用jsonp . </p>
 * <p>Description
 * 1. 可以通过spring 的xml配置实现此类一样功能
 * </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/27 17:15
 */
//@ControllerAdvice(basePackages = "com.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}
