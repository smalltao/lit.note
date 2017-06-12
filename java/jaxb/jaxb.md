# java bean xml 转换工具
> http://jaxb.java.net/

## bean转换xml
> bean

```
package com.sogou.reventon.bean;
import com.mchange.v2.naming.JavaBeanObjectFactory;
import javax.naming.spi.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name="ArrayOfstring")
public class ArrayOfDetectRequest {
    private ArrayList<String> text;
    public ArrayList<String> getText() {
        return text;
    }
    @XmlElement(name = "string", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays")
    public void setText(ArrayList<String> text) {
        this.text = text;
    }
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfDetectRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        ArrayOfDetectRequest arrayOfDetectRequest = new ArrayOfDetectRequest();
        ArrayList<String> list = new ArrayList<>();
        list.add("litao");
        arrayOfDetectRequest.setText(list);
//        格式化输出
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(arrayOfDetectRequest, System.out);
    }
}
```

> 转换

```
ArrayOfDetectRequest arrayOfDetectRequest = new ArrayOfDetectRequest();
ArrayList<String> list = new ArrayList<>();
list.add("litao");
arrayOfDetectRequest.setText(list);

File file = new File("C:\\file.xml");

JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfDetectRequest.class);
Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

// 输出编码
marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
是否带有<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//格式化输出
jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//输出到控制台
jaxbMarshaller.marshal(arrayOfDetectRequest, System.out);
//输出写入文件
jaxbMarshaller.marshal(arrayOfDetectRequest, file);
//输出文件到字符串
ByteArrayOutputStream baos = new ByteArrayOutputStream();
jaxbMarshaller.marshal(arrayOfDetectRequest, baos);
String body = new String(baos.toByteArray());
System.out.println(body);
```

> 输出结果

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ArrayOfstring xmlns:ns2="http://schemas.microsoft.com/2003/10/Serialization/Arrays">
    <ns2:string>litao</ns2:string>
</ArrayOfstring>
```

## xml 转换bean
```
JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfDetectResponse.class);
Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
ByteArrayInputStream bis = new ByteArrayInputStream(queryRequest.getFetchBytes());
ArrayOfDetectResponse arrayOfDetectResponse = (ArrayOfDetectResponse) unmarshaller.unmarshal(bis);
queryRequest.setResult(JSONObject.fromObject(arrayOfDetectResponse).toString());
```


### 自定义命名空间
http://www.cnblogs.com/lzrabbit/p/3662159.html
### 忽略命名空间前缀
http://www.cnblogs.com/lzrabbit/p/3662160.html