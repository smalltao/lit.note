# spring 内容协商解析器使用
> 需要指定 headers中的 Accept 和 Content-Typed

```
$.ajax({
 url: "testResponseBody",
 type: 'GET',
 headers: {
     Accept: "application/xml",
//        Accept:"application/json",
 },
 success: function(data, textStatus){
     console.log(data);
     alert(data);
 },
 error: function (data, textStatus, errorThrown) {
     console.log(data);
 },
     });
```

# @RequestBody 使用

## 作用
> 注解用于将 Controller 的方法参数，根据HTTP Request Header的content-Type的内容,通过适当的HttpMessageConverter转换为JAVA类

## 时机
> POST或者PUT的数据是JSON格式或者XML格式,而不是普通的键值对形式.

1. 控制器代码
```
@RequestMapping(value = "/testRequestBody", method= RequestMethod.POST)
  @ResponseBody
  public Person testRequestBody(@RequestBody Person p) {
      System.out.println("creating a employee:" + p);
      return p;
  }
```

2. Ajax 代码
```
$.ajax({
  url: "testRequestBody",
  data: '{"name":"小红","age":12}', //要用双引号!!
  contentType: "application/json;charset=utf-8", // 因为上面是JSON数据

  type: "POST",
  headers: {
//                Accept: "application/xml",
      Accept: "application/json",
  },
  success: function(data, textStatus){
      console.log(data);
      alert(data);
  },
  error: function (data, textStatus, errorThrown) {
      console.log(data);
  },
});
```

