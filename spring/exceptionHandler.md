# spring 注解 @ExceptionHandler spring 异常拦截

```
当这个控制器中任意一个方法抛出运行时异常，会被spring拦截，封装false到map
@Controller
public class AccessController {
    @ExceptionHandler(RuntimeException.class)        
    public @ResponseBody Map<String, Object> runtimeExceptionHandler(RuntimeException runtimeException) {
        logger.error(runtimeException.getLocalizedMessage());
        Map model = new TreeMap();
        model.put("status", false);
        return model;
    }
}
```bash
