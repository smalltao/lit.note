# json 复杂对象转换错误
> 在使用JSONObject.toBean的时候，得到的Bean里面的复杂数据类型不能转换成需要的对象类型。
> java.lang.ClassCastException: net.sf.ezmorph.bean.MorphDynaBean cannot be cast to com.edu.xukai.Student at com.edu.xukai.TestJSONObject.main(TestJSONObject.java:25)

> 解决办法：使用JSONObject.toBean(jsonObject, beanClass, classMap) 
