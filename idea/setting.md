## 内存溢出
1、使用idea64.exe启动
2、修改idea.exe.vmoptions

## 类继承了Serializable接口之后，使用alt+enter快捷键自动创建序列化id
进入setting→inspections→serialization issues→选择图中的选项。serializable class without ‘serialVersionUID’

## 自定义类注释、方法注释
```
settings >> Live Templates >> + >> template group >> define
类注释
/**
 * <p>Title:      $className$.$END$ </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     $date$ $time$
 */
 方法注释
 /**
  * <p>Title:      $methodName$.$END$ </p>
  * <p>Description </p>
  *
  * @param         $param$
  * @Author        <a href="litaoos2862@sogou-inc.com"/>litaoos2862</a>
  * @CreateDate    $date$ $time$
  * @return        $return$
  */
```