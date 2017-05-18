# spring ע�� @ExceptionHandler spring �쳣����

```
�����������������һ�������׳�����ʱ�쳣���ᱻspring���أ���װfalse��map
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
