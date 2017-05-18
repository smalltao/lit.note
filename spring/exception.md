# spring mvc 全局异常捕获

> `web.xml` 指定`error-code`和`page`到指定地址

> 使用`spring mvc` 的全局异常捕获功能， 可以根据需要做一些后续处理，比如日志记录

## 定义服务器 `web.xml` 整合到`spring mvc` 中
1. `web.xml`
```
<error-page>
    <error-code>404</error-code>
    <location>/404</location>
</error-page>
<error-page>
    <error-code>500</error-code>
    <location>/500</location>
</error-page>
<!-- 未捕获的错误，也可以指定其它异常类，或自定影异常类 -->
<error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/uncaughtException</location>
<error-page>
```bash
```
2. `applicationContext.xml` 
```
<!-- 错误路径和错误页面，注意指定viewResolver -->
<mvc:view-controller path="/404" view-name="404" />
<mvc:view-controller path="/500" view-name="500" />
<mvc:view-controller path="/uncaughException" view-name="uncaughtEeception" />
```bash

## spring 全局异常 
1. 代码入侵方式

> 异常抛出
```
@Controller
public class MainController {
    @ResponseBody
    @RequestMapping("/")
    public String main() {
        throw new NullPointerException("NullPointerException Test!");
    }
}
```bash

> 异常捕获
```
//注意使用注解@ControllerAdvice作用域是全局Controller范围
//@ControllerAdvice 可以应用到所有@RequestMapping类或方法上的@ExceptionHandler、 @InitBinder 、@ModelAttribute 在这里是@ExceptionHandler
@ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIOException(NullPointException ex) {
        return ClassUtils.getShortName(ex.getClass()) + ex.getMessage();
    }
}
```bash

2. 配置方式 

> 异常抛出 同上

> 异常捕获 
```
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <!-- 默认错误页面，不在execeptionMappings指定范围内 -->
    <property name="defaultErrorView" value="uncaughtException" />
    <property name="exceptionMapping">
        <props>
            <!-- 异常类名，可以是全路径，错误页面或Controller路径！ -->
            <prop key=".NulPointerException">NullPointerException</prop>
            <prop key="java.io.IOException">IOException</prop>
        </props>
    </property>
</bean>
```bash

3. 自定义异常类和异常解析

> 自定义异常类：
```
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }
    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
        //do something ...
    }
}
```bash

> 异常抛出
```
@ResponseBody
@RequestMapping("/ce")
public String ce(CustomException e) {
    thorw new CustomException("msg", e);
}
```bash

> 实现异常捕获接口 HandlerExceptionResolver
```
public class CustomHandlerExceptionResolver implements HandlerExceptoinResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        model.put("e", e);
        //这里可以根据不同异常，做不同的处理凡是
        String viewName = ClassUtils.getShortName(e.getClass());
        return new ModelAndView(viewName, model);
    }
}
```bash

> 配置Spring 支持异常捕获
```
<bean class="cn.bg.controller.CustomHandlerExceptionResolver">
```bash
