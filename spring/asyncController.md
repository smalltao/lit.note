# spring mvc 异步控制器
> 仅支持servlet 3.0 及以上版本 且需要添加以下依赖
```
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.1.0</version>
  <scope>provided</scope>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>4.2.3.RELEASE</version>
</dependency>
```
> 传统的同步控制器返回 `ModelAndView` 异步的返回 `DeferredResult<ModelAndView>` 
```
@RequestMapping(value="/asynctask", method = RequestMethod.GET)
public DeferredResult<ModelAndView> asyncTask(){
    DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
    System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
    longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
        @Override
        public void callback(Object result) {
            System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
            ModelAndView mav = new ModelAndView("remotecalltask");
            mav.addObject("result", result);
            deferredResult.setResult(mav);
        }
    });
}
```

