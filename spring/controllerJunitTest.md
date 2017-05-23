# 使用junit 测试 Controller
> 使用mockmvc 对象模拟浏览器请求
## 依赖
> 项目依赖
```
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-context</artifactId>  
    <version>${spring.version}</version>  
</dependency>  
  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-webmvc</artifactId>  
    <version>${spring.version}</version>  
</dependency>
```
> 测试依赖
```
<dependency>  
    <groupId>junit</groupId>  
    <artifactId>junit</artifactId>  
    <version>${junit.version}</version>  
    <scope>test</scope>  
</dependency>  
<dependency>  
    <groupId>org.hamcrest</groupId>  
    <artifactId>hamcrest-core</artifactId>  
    <version>${hamcrest.core.version}/version>  
    <scope>test</scope>  
</dependency>  
<dependency>  
    <groupId>org.mockito</groupId>  
    <artifactId>mockito-core</artifactId>  
    <version>${mockito.core.version}</version>  
    <scope>test</scope>  
</dependency>  
  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-test</artifactId>  
    <version>${spring.version}</version>  
    <scope>test</scope>  
</dependency>  
```
## `xml` 风格配置
> spring-config.xml：加载非web层组件 
```
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
       xmlns:context="http://www.springframework.org/schema/context"  
       xsi:schemaLocation="  
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd  
       ">  
    <!-- 通过web.xml中的 org.springframework.web.context.ContextLoaderListener 加载的  -->  
    <!-- 请参考 http://jinnianshilongnian.iteye.com/blog/1602617  -->  
    <context:component-scan base-package="com.sishuok.mvc">  
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>  
    </context:component-scan>  
</beans>  
```
> spring-mvc.xml：加载和配置web层组件 
```
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
       xmlns:context="http://www.springframework.org/schema/context"  
       xmlns:mvc="http://www.springframework.org/schema/mvc"  
       xsi:schemaLocation="  
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd  
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd  
       ">  
    <!-- 通过web.xml中的 org.springframework.web.servlet.DispatcherServlet 加载的  -->  
    <!-- 请参考 http://jinnianshilongnian.iteye.com/blog/1602617  -->  
    <context:component-scan base-package="com.sishuok.mvc" use-default-filters="false">  
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>  
    </context:component-scan>  
    <mvc:annotation-driven/>  
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
        <property name="prefix" value="/WEB-INF/jsp/"/>  
        <property name="suffix" value=".jsp"/>  
    </bean>  
</beans>  
```
> `web.xml` 配置
```
<?xml version="1.0" encoding="UTF-8"?>  
<web-app  
        xmlns="http://java.sun.com/xml/ns/javaee"  
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"  
        version="3.0"  
        metadata-complete="false">  
  
    <!-- 请禁用com.sishuok.config.WebInitializer后开启如下配置 -->  
  
    <!-- Spring配置文件开始  -->  
    <context-param>  
        <param-name>contextConfigLocation</param-name>  
        <param-value>  
            classpath:spring-config.xml  
        </param-value>  
    </context-param>  
    <listener>  
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>  
    </listener>  
    <!-- Spring配置文件结束 -->  
  
    <!-- 设置servlet编码开始 -->  
    <filter>  
        <filter-name>Set Character Encoding</filter-name>  
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
        <async-supported>true</async-supported>  
        <init-param>  
            <param-name>encoding</param-name>  
            <param-value>UTF-8</param-value>  
        </init-param>  
        <init-param>  
            <param-name>forceEncoding</param-name>  
            <param-value>true</param-value>  
        </init-param>  
    </filter>  
    <filter-mapping>  
        <filter-name>Set Character Encoding</filter-name>  
        <url-pattern>/*</url-pattern>  
    </filter-mapping>  
    <!-- 设置servlet编码结束 -->  
  
    <servlet>  
        <servlet-name>spring</servlet-name>  
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>  
        <init-param>  
            <param-name>contextConfigLocation</param-name>  
            <param-value>classpath:spring-mvc.xml</param-value>  
        </init-param>  
        <load-on-startup>1</load-on-startup>  
        <async-supported>true</async-supported>  
    </servlet>  
    <servlet-mapping>  
        <servlet-name>spring</servlet-name>  
        <url-pattern>/</url-pattern>  
    </servlet-mapping>  
</web-app>  
```
> 等价的注解风格配置：  AppConfig.java：等价于spring-config.xml
```
package com.sishuok.config;  
  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.context.annotation.FilterType;  
import org.springframework.stereotype.Controller;  
  
@Configuration  
@ComponentScan(basePackages = "com.sishuok.mvc", excludeFilters = {  
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})  
})  
public class AppConfig {  
}  
```
>　WebInitializer.java：注册相应的web.xml中的组件
```
package com.sishuok.config;  
  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.ContextLoaderListener;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.filter.CharacterEncodingFilter;  
import org.springframework.web.servlet.DispatcherServlet;  
  
import javax.servlet.DispatcherType;  
import javax.servlet.FilterRegistration;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration;  
import java.util.EnumSet;  
  
public class WebInitializer implements WebApplicationInitializer {  
  
    @Override  
    public void onStartup(javax.servlet.ServletContext sc) throws ServletException {  
  
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();  
        rootContext.register(AppConfig.class);  
        sc.addListener(new ContextLoaderListener(rootContext));  
  
        //2、springmvc上下文  
        AnnotationConfigWebApplicationContext springMvcContext = new AnnotationConfigWebApplicationContext();  
        springMvcContext.register(MvcConfig.class);  
        //3、DispatcherServlet  
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcContext);  
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);  
        dynamic.setLoadOnStartup(1);  
        dynamic.addMapping("/");  
  
        //4、CharacterEncodingFilter  
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
        characterEncodingFilter.setEncoding("utf-8");  
        FilterRegistration filterRegistration =  
                sc.addFilter("characterEncodingFilter", characterEncodingFilter);  
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");  
  
    }  
}  
```
## 测试方法
### 独立测试方式
```
public class UserControllerStandaloneSetupTest {  
    private MockMvc mockMvc;  
    @Before  
    public void setUp() {  
        UserController userController = new UserController();  
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();  
    }  
}  
```
1. 首先自己创建相应的控制器，注入相应的依赖
2. 通过MockMvcBuilders.standaloneSetup模拟一个Mvc测试环境，通过build得到一个MockMvc
3. MockMvc：是我们以后测试时经常使用的API，后边介绍

### 集成Web环境方式
```
//XML风格  
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration(value = "src/main/webapp")  
@ContextHierarchy({  
        @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
})  
  
//注解风格  
//@RunWith(SpringJUnit4ClassRunner.class)  
//@WebAppConfiguration(value = "src/main/webapp")  
//@ContextHierarchy({  
//        @ContextConfiguration(name = "parent", classes = AppConfig.class),  
//        @ContextConfiguration(name = "child", classes = MvcConfig.class)  
//})  
public class UserControllerWebAppContextSetupTest {  
  
    @Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;  
  
    @Before  
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
    }  
}  
```
1. @WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
2. @ContextHierarchy：指定容器层次，即spring-config.xml是父容器，而spring-mvc.xml是子容器
3. 通过@Autowired WebApplicationContext wac：注入web环境的ApplicationContext容器；
4. 然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试；

### 测试 
```
@Test  
public void testView() throws Exception {  
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))  
            .andExpect(MockMvcResultMatchers.view().name("user/view"))  
            .andExpect(MockMvcResultMatchers.model().attributeExists("user"))  
            .andDo(MockMvcResultHandlers.print())  
            .andReturn();  
      
    Assert.assertNotNull(result.getModelAndView().getModel().get("user"));  
}  
```
1. mockMvc.perform执行一个请求；
2. MockMvcRequestBuilders.get("/user/1")构造一个请求
3. ResultActions.andExpect添加执行完成后的断言
4. ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
5. ResultActions.andReturn表示执行完成后返回相应的结果。
6. setDefaultRequest：设置默认的RequestBuilder，用于在每次perform执行相应的RequestBuilder时自动把该默认的RequestBuilder合并到perform的RequestBuilder中；
7. setGlobalResultMatchers：设置全局的预期结果验证规则，如我们通过MockMvc测试多个控制器时，假设它们都想验证某个规则时，就可以使用这个；
8. setGlobalResultHandlers：设置全局的ResultHandler结果处理器；

### 测试api
> 静态工厂方法法： MockMvcBuilders、MockMvcRequestBuilders、MockMvcResultMatchers、MockMvcResultHandlers。
```
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
```
#### Servlet/JSP API Mock  
> 提供了对Servlet 3 相应API的Mock 具体请查看spring-test模块的org.springframework.mock.web包。

#### MockMvcBuilder/MockMvcBuilders
> MockMvcBuilder 是MockMvc的构造器，其主要有两个实现：StandaloneMockMvcBuilder和DefaultMockMvcBuilder
> MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc
> MockMvcBuilders.standaloneSetup(Object... controllers)：通过参数指定一组控制器，这样就不需要从上下文获取了

> DefaultMockMvcBuilder 还提供了如下API：
1. addFilters(Filter... filters)/addFilter(Filter filter, String... urlPatterns)：添加javax.servlet.Filter过滤器
2. defaultRequest(RequestBuilder requestBuilder)：默认的RequestBuilder，每次执行时会合并到自定义的RequestBuilder中，即提供公共请求数据的；
3. alwaysExpect(ResultMatcher resultMatcher)：定义全局的结果验证器，即每次执行请求时都进行验证的规则；
4. alwaysDo(ResultHandler resultHandler)：定义全局结果处理器，即每次请求时都进行结果处理；
5. dispatchOptions：DispatcherServlet是否分发OPTIONS请求方法到控制器；

> StandaloneMockMvcBuilder继承了DefaultMockMvcBuilder，又提供了如下API：
1. setMessageConverters(HttpMessageConverter<?>...messageConverters)：设置HTTP消息转换器；
2. setValidator(Validator validator)：设置验证器；
3. setConversionService(FormattingConversionService conversionService)：设置转换服务；
4. addInterceptors(HandlerInterceptor... interceptors)/addMappedInterceptors(String[] pathPatterns, HandlerInterceptor... interceptors)：添加spring mvc拦截器；
5. setContentNegotiationManager(ContentNegotiationManager contentNegotiationManager)：设置内容协商管理器；
6. setAsyncRequestTimeout(long timeout)：设置异步超时时间；
7. setCustomArgumentResolvers(HandlerMethodArgumentResolver... argumentResolvers)：设置自定义控制器方法参数解析器；
8. setCustomReturnValueHandlers(HandlerMethodReturnValueHandler... handlers)：设置自定义控制器方法返回值处理器；
9. setHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers)/setHandlerExceptionResolvers(HandlerExceptionResolver... exceptionResolvers)：设置异常解析器；
10. setViewResolvers(ViewResolver...resolvers)：设置视图解析器；
11. setSingleView(View view)：设置单个视图，即视图解析时总是解析到这一个（仅适用于只有一个视图的情况）；
12. setLocaleResolver(LocaleResolver localeResolver)：设置Local解析器；
13. setFlashMapManager(FlashMapManager flashMapManager)：设置FlashMapManager，如存储重定向数据；
14. setUseSuffixPatternMatch(boolean useSuffixPatternMatch)：设置是否是后缀模式匹配，如“/user”是否匹配"/user.*"，默认真即匹配；
15. setUseTrailingSlashPatternMatch(boolean useTrailingSlashPatternMatch)：设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
16. addPlaceHolderValue(String name, String value) ：添加request mapping中的占位符替代；
> 因为StandaloneMockMvcBuilder不会加载Spring MVC配置文件，因此就不会注册我们需要的一些组件，因此就提供了如上API用于注册我们需要的相应组件。

#### RequestBuilder/MockMvcRequestBuilders
> RequestBuilder用来构建请求的，其提供了一个方法buildRequest(ServletContext servletContext)用于构建MockHttpServletRequest；
> 其主要有两个子类MockHttpServletRequestBuilder和MockMultipartHttpServletRequestBuilder（如文件上传使用），即用来Mock客户端请求需要的所有数据。
> MockMvcRequestBuilders主要API：
1. MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的MockHttpServletRequestBuilder；如get("/user/{id}", 1L)；
2. MockHttpServletRequestBuilder post(String urlTemplate, Object... urlVariables)：同get类似，但是是POST方法；
3. MockHttpServletRequestBuilder put(String urlTemplate, Object... urlVariables)：同get类似，但是是PUT方法；
4. MockHttpServletRequestBuilder delete(String urlTemplate, Object... urlVariables) ：同get类似，但是是DELETE方法；
5. MockHttpServletRequestBuilder options(String urlTemplate, Object... urlVariables)：同get类似，但是是OPTIONS方法；
6. MockHttpServletRequestBuilder request(HttpMethod httpMethod, String urlTemplate, Object... urlVariables)：提供自己的Http请求方法及uri模板和uri变量，如上API都是委托给这个API；
7. MockMultipartHttpServletRequestBuilder fileUpload(String urlTemplate, Object... urlVariables)：提供文件上传方式的请求，得到MockMultipartHttpServletRequestBuilder；
8. RequestBuilder asyncDispatch(final MvcResult mvcResult)：创建一个从启动异步处理的请求的MvcResult进行异步分派的RequestBuilder；
 
> MockHttpServletRequestBuilder和MockMultipartHttpServletRequestBuilder API：
> MockHttpServletRequestBuilder API：
1. MockHttpServletRequestBuilder header(String name, Object... values)/MockHttpServletRequestBuilder headers(HttpHeaders httpHeaders)：添加头信息；
2. MockHttpServletRequestBuilder contentType(MediaType mediaType)：指定请求的contentType头信息；
3. MockHttpServletRequestBuilder accept(MediaType... mediaTypes)/MockHttpServletRequestBuilder accept(String... mediaTypes)：指定请求的Accept头信息；
4. MockHttpServletRequestBuilder content(byte[] content)/MockHttpServletRequestBuilder content(String content)：指定请求Body体内容；
5. MockHttpServletRequestBuilder cookie(Cookie... cookies)：指定请求的Cookie；
6. MockHttpServletRequestBuilder locale(Locale locale)：指定请求的Locale；
7. MockHttpServletRequestBuilder characterEncoding(String encoding)：指定请求字符编码；
8. MockHttpServletRequestBuilder requestAttr(String name, Object value) ：设置请求属性数据；
9. MockHttpServletRequestBuilder sessionAttr(String name, Object value)/MockHttpServletRequestBuilder sessionAttrs(Map<String, Object> sessionAttributes)：设置请求session属性数据；
10. MockHttpServletRequestBuilder flashAttr(String name, Object value)/MockHttpServletRequestBuilder flashAttrs(Map<String, Object> flashAttributes)：指定请求的flash信息，比如重定向后的属性信息；
11. MockHttpServletRequestBuilder session(MockHttpSession session) ：指定请求的Session；
12. MockHttpServletRequestBuilder principal(Principal principal) ：指定请求的Principal；
13. MockHttpServletRequestBuilder contextPath(String contextPath) ：指定请求的上下文路径，必须以“/”开头，且不能以“/”结尾；
14. MockHttpServletRequestBuilder pathInfo(String pathInfo) ：请求的路径信息，必须以“/”开头；
15. MockHttpServletRequestBuilder secure(boolean secure)：请求是否使用安全通道；
16. MockHttpServletRequestBuilder with(RequestPostProcessor postProcessor)：请求的后处理器，用于自定义一些请求处理的扩展点；
 
> MockMultipartHttpServletRequestBuilder继承自MockHttpServletRequestBuilder，又提供了如下API：
1. MockMultipartHttpServletRequestBuilder file(String name, byte[] content)/MockMultipartHttpServletRequestBuilder file(MockMultipartFile file)：指定要上传的文件；

#### ResultActions
> 调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，通过ResultActions完成如下三件事：
1. ResultActions andExpect(ResultMatcher matcher) ：添加验证断言来判断执行请求后的结果是否是预期的；
2. ResultActions andDo(ResultHandler handler) ：添加结果处理器，用于对验证成功后执行的动作，如输出下请求/结果信息用于调试；
3. MvcResult andReturn() ：返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；
 
#### ResultMatcher/MockMvcResultMatchers
> ResultMatcher用来匹配执行完请求后的结果验证，其就一个match(MvcResult result)断言方法，如果匹配失败将抛出相应的异常；
> spring mvc测试框架提供了很多***ResultMatchers来满足测试需求。注意这些***ResultMatchers并不是ResultMatcher的子类，而是返回ResultMatcher实例的。
> Spring mvc测试框架为了测试方便提供了MockMvcResultMatchers静态工厂方法方便操作；具体的API如下：
1. HandlerResultMatchers handler()：请求的Handler验证器，比如验证处理器类型/方法名；此处的Handler其实就是处理请求的控制器；
2. RequestResultMatchers request()：得到RequestResultMatchers验证器；
3. ModelResultMatchers model()：得到模型验证器；
4. ViewResultMatchers view()：得到视图验证器；
5. FlashAttributeResultMatchers flash()：得到Flash属性验证；
6. StatusResultMatchers status()：得到响应状态验证器；
7. HeaderResultMatchers header()：得到响应Header验证器；
8. CookieResultMatchers cookie()：得到响应Cookie验证器；
9. ContentResultMatchers content()：得到响应内容验证器；
10. JsonPathResultMatchers jsonPath(String expression, Object ... args)/ResultMatcher jsonPath(String expression, Matcher<T> matcher)：得到Json表达式验证器；
11. XpathResultMatchers xpath(String expression, Object... args)/XpathResultMatchers xpath(String expression, Map<String, String> namespaces, Object... args)：得到Xpath表达式验证器；
12. ResultMatcher forwardedUrl(final String expectedUrl)：验证处理完请求后转发的url（绝对匹配）；
13. ResultMatcher forwardedUrlPattern(final String urlPattern)：验证处理完请求后转发的url（Ant风格模式匹配，@since spring4）；
14. ResultMatcher redirectedUrl(final String expectedUrl)：验证处理完请求后重定向的url（绝对匹配）；
15. ResultMatcher redirectedUrlPattern(final String expectedUrl)：验证处理完请求后重定向的url（Ant风格模式匹配，@since spring4）；
 
> 得到相应的***ResultMatchers后，接着再调用其相应的API得到ResultMatcher，如ModelResultMatchers.attributeExists(final String... names)判断Model属性是否存在。
 
#### ResultHandler/MockMvcResultHandlers
> ResultHandler用于对处理的结果进行相应处理的，比如输出整个请求/响应等信息方便调试，Spring mvc测试框架提供了MockMvcResultHandlers静态工厂方法，
> 该工厂提供了ResultHandler print()返回一个输出MvcResult详细信息到控制台的ResultHandler实现。
 
#### MvcResult
> 即执行完控制器后得到的整个结果，并不仅仅是返回值，其包含了测试时需要的所有信息，如：
1. MockHttpServletRequest getRequest()：得到执行的请求；
2. MockHttpServletResponse getResponse()：得到执行后的响应；
3. Object getHandler()：得到执行的处理器，一般就是控制器；
4. HandlerInterceptor[] getInterceptors()：得到对处理器进行拦截的拦截器；
5. ModelAndView getModelAndView()：得到执行后的ModelAndView；
6. Exception getResolvedException()：得到HandlerExceptionResolver解析后的异常；
7. FlashMap getFlashMap()：得到FlashMap；
8. Object getAsyncResult()/Object getAsyncResult(long timeout)：得到异步执行的结果；

### 例子
1. 测试普通控制器
```
mockMvc.perform(get("/user/{id}", 1)) //执行请求  
        .andExpect(model().attributeExists("user")) //验证存储模型数据  
        .andExpect(view().name("user/view")) //验证viewName  
        .andExpect(forwardedUrl("/WEB-INF/jsp/user/view.jsp"))//验证视图渲染时forward到的jsp  
        .andExpect(status().isOk())//验证状态码  
        .andDo(print()); //输出MvcResult到控制台  
```
2. 得到MvcResult自定义验证    
```
MvcResult result = mockMvc.perform(get("/user/{id}", 1))//执行请求  
        .andReturn(); //返回MvcResult  
Assert.assertNotNull(result.getModelAndView().getModel().get("user")); //自定义断言  
```
3. 验证请求参数绑定到模型数据及Flash属性 
```
mockMvc.perform(post("/user").param("name", "zhang")) //执行传递参数的POST请求(也可以post("/user?name=zhang"))  
        .andExpect(handler().handlerType(UserController.class)) //验证执行的控制器类型  
        .andExpect(handler().methodName("create")) //验证执行的控制器方法名  
        .andExpect(model().hasNoErrors()) //验证页面没有错误  
        .andExpect(flash().attributeExists("success")) //验证存在flash属性  
        .andExpect(view().name("redirect:/user")); //验证视图  
```
4. 验证请求参数验证失败出错  
```
mockMvc.perform(post("/user").param("name", "admin")) //执行请求  
        .andExpect(model().hasErrors()) //验证模型有错误  
        .andExpect(model().attributeDoesNotExist("name")) //验证存在错误的属性  
        .andExpect(view().name("showCreateForm")); //验证视图  
```
 
5. 
文件上传 
```
byte[] bytes = new byte[] {1, 2};  
mockMvc.perform(fileUpload("/user/{id}/icon", 1L).file("icon", bytes)) //执行文件上传  
        .andExpect(model().attribute("icon", bytes)) //验证属性相等性  
        .andExpect(view().name("success")); //验证视图  
``` 
 
6. JSON请求/响应验证
> 测试时需要安装jackson Json和JsonPath依赖： 
```
<dependency>  
    <groupId>com.fasterxml.jackson.core</groupId>  
    <artifactId>jackson-databind</artifactId>  
    <version>${jackson2.version}</version>  
</dependency>  
<dependency>  
    <groupId>com.jayway.jsonpath</groupId>  
    <artifactId>json-path</artifactId>  
    <version>${jsonpath.version}</version>  
    <scope>test</scope>  
</dependency>
```
```
String requestBody = "{\"id\":1, \"name\":\"zhang\"}";  
mockMvc.perform(post("/user")  
            .contentType(MediaType.APPLICATION_JSON).content(requestBody)  
            .accept(MediaType.APPLICATION_JSON)) //执行请求  
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType  
        .andExpect(jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/  
  
String errorBody = "{id:1, name:zhang}";  
MvcResult result = mockMvc.perform(post("/user")  
        .contentType(MediaType.APPLICATION_JSON).content(errorBody)  
        .accept(MediaType.APPLICATION_JSON)) //执行请求  
        .andExpect(status().isBadRequest()) //400错误请求  
        .andReturn();  
  
Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体  
```

7. XML请求/响应验证
> 测试时需要安装spring oxm和xstream依赖： 
```
<dependency>  
    <groupId>com.thoughtworks.xstream</groupId>  
    <artifactId>xstream</artifactId>  
    <version>${xsream.version}</version>  
    <scope>test</scope>  
</dependency>  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-oxm</artifactId>  
    <version>${spring.version}</version>  
    <scope>test</scope>  
</dependency>  
```
```
String requestBody = "<user><id>1</id><name>zhang</name></user>";  
mockMvc.perform(post("/user")  
        .contentType(MediaType.APPLICATION_XML).content(requestBody)  
        .accept(MediaType.APPLICATION_XML)) //执行请求  
        .andDo(print())  
        .andExpect(content().contentType(MediaType.APPLICATION_XML)) //验证响应contentType  
        .andExpect(xpath("/user/id/text()").string("1")); //使用XPath表达式验证XML 请参考http://www.w3school.com.cn/xpath/  
  
String errorBody = "<user><id>1</id><name>zhang</name>";  
MvcResult result = mockMvc.perform(post("/user")  
        .contentType(MediaType.APPLICATION_XML).content(errorBody)  
        .accept(MediaType.APPLICATION_XML)) //执行请求  
        .andExpect(status().isBadRequest()) //400错误请求  
        .andReturn();  
  
Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体  
```
 
8. 异常处理  
```
MvcResult result = mockMvc.perform(get("/user/exception")) //执行请求  
        .andExpect(status().isInternalServerError()) //验证服务器内部错误  
        .andReturn();  
  
Assert.assertTrue(IllegalArgumentException.class.isAssignableFrom(result.getResolvedException().getClass()));  
```
 

9. 静态资源 
```
mockMvc.perform(get("/static/app.js")) //执行请求  
        .andExpect(status().isOk()) //验证状态码200  
        .andExpect(content().string(CoreMatchers.containsString("var")));//验证渲染后的视图内容包含var  
  
mockMvc.perform(get("/static/app1.js")) //执行请求  
        .andExpect(status().isNotFound());  //验证状态码404  
```

10. 异步测试 
```
//Callable  
MvcResult result = mockMvc.perform(get("/user/async1?id=1&name=zhang")) //执行请求  
        .andExpect(request().asyncStarted())  
        .andExpect(request().asyncResult(CoreMatchers.instanceOf(User.class))) //默认会等10秒超时  
        .andReturn();  
  
mockMvc.perform(asyncDispatch(result))  
        .andExpect(status().isOk())  
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))  
        .andExpect(jsonPath("$.id").value(1));  
        
//DeferredResult  
result = mockMvc.perform(get("/user/async2?id=1&name=zhang")) //执行请求  
        .andExpect(request().asyncStarted())  
        .andExpect(request().asyncResult(CoreMatchers.instanceOf(User.class)))  //默认会等10秒超时  
        .andReturn();  
  
mockMvc.perform(asyncDispatch(result))  
        .andExpect(status().isOk())  
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))  
        .andExpect(jsonPath("$.id").value(1));  
此处请在第一次请求时加上 andExpect(request().asyncResult(CoreMatchers.instanceOf(User.class)))这样会等待结果返回/超时，无须自己设置线程等待了；此处注意request().asyncResult一定是在第一次请求发出；然后第二次通过asyncDispatch进行异步请求。
 
```
11. 添加自定义过滤器
```
mockMvc = webAppContextSetup(wac).addFilter(new MyFilter(), "/*").build();  
mockMvc.perform(get("/user/1"))  
        .andExpect(request().attribute("filter", true));  
```
 
12. 全局配置 
```
mockMvc = webAppContextSetup(wac)  
        .defaultRequest(get("/user/1").requestAttr("default", true)) //默认请求 如果其是Mergeable类型的，会自动合并的哦mockMvc.perform中的RequestBuilder  
        .alwaysDo(print())  //默认每次执行请求后都做的动作  
        .alwaysExpect(request().attribute("default", true)) //默认每次执行后进行验证的断言  
        .build();  
  
mockMvc.perform(get("/user/1"))  
        .andExpect(model().attributeExists("user"));  
```
```
<!--mock 测试相关-->
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest-core</artifactId>
    <version>1.3</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>2.7.22</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>3.2.3.RELEASE</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.1.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.jayway.jsonpath</groupId>
    <artifactId>json-path</artifactId>
    <version>2.2.0</version>
    <scope>test</scope>
</dependency>
<!-- AES 加密相关包-->
<dependency>
    <groupId>org.bouncycastle</groupId>
    <artifactId>bcprov-jdk15on</artifactId>
    <version>1.50</version>
</dependency>
```

 