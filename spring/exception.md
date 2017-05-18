# spring mvc ȫ���쳣����

> `web.xml` ָ��`error-code`��`page`��ָ����ַ

> ʹ��`spring mvc` ��ȫ���쳣�����ܣ� ���Ը�����Ҫ��һЩ��������������־��¼

## ��������� `web.xml` ���ϵ�`spring mvc` ��
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
<!-- δ����Ĵ���Ҳ����ָ�������쳣�࣬���Զ�Ӱ�쳣�� -->
<error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/uncaughtException</location>
<error-page>
```bash
```
2. `applicationContext.xml` 
```
<!-- ����·���ʹ���ҳ�棬ע��ָ��viewResolver -->
<mvc:view-controller path="/404" view-name="404" />
<mvc:view-controller path="/500" view-name="500" />
<mvc:view-controller path="/uncaughException" view-name="uncaughtEeception" />
```bash

## spring ȫ���쳣 
1. �������ַ�ʽ

> �쳣�׳�
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

> �쳣����
```
//ע��ʹ��ע��@ControllerAdvice��������ȫ��Controller��Χ
//@ControllerAdvice ����Ӧ�õ�����@RequestMapping��򷽷��ϵ�@ExceptionHandler�� @InitBinder ��@ModelAttribute ��������@ExceptionHandler
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

2. ���÷�ʽ 

> �쳣�׳� ͬ��

> �쳣���� 
```
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <!-- Ĭ�ϴ���ҳ�棬����execeptionMappingsָ����Χ�� -->
    <property name="defaultErrorView" value="uncaughtException" />
    <property name="exceptionMapping">
        <props>
            <!-- �쳣������������ȫ·��������ҳ���Controller·���� -->
            <prop key=".NulPointerException">NullPointerException</prop>
            <prop key="java.io.IOException">IOException</prop>
        </props>
    </property>
</bean>
```bash

3. �Զ����쳣����쳣����

> �Զ����쳣�ࣺ
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

> �쳣�׳�
```
@ResponseBody
@RequestMapping("/ce")
public String ce(CustomException e) {
    thorw new CustomException("msg", e);
}
```bash

> ʵ���쳣����ӿ� HandlerExceptionResolver
```
public class CustomHandlerExceptionResolver implements HandlerExceptoinResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        model.put("e", e);
        //������Ը��ݲ�ͬ�쳣������ͬ�Ĵ�����
        String viewName = ClassUtils.getShortName(e.getClass());
        return new ModelAndView(viewName, model);
    }
}
```bash

> ����Spring ֧���쳣����
```
<bean class="cn.bg.controller.CustomHandlerExceptionResolver">
```bash
