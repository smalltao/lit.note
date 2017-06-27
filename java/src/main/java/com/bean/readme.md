# 以下内容配合数据库生成工具可实现 有数据库 mysql >> bean >> xsd >> xmlbean >> xml 的过程 搭配spring 内容协商视图解析器可以实现不同返回结果的视图

## bean 代码与 xsd 互换 生成xsd
> Idea Tools > JAXB > Generate xml Schema from java using jaxb 弹出窗选择 Include parameter and return types of following methods

## 从xsd生成java bean定义
> 在编辑器中打开xsd文件，点击Tools -> JAXB -> Generate Java Code From Xml Schema Using JAXB，在弹出的窗口中根据需要进行相应设置，其中 Package prefix是生成的类的包名。这样就生成了bean的实体

# XmlBean
> 这个功能是由WebServices插件提供的，需要本地安装XmlBeans，使用前需要在File -> Settings -> Build, Execution, Deployment -> Web Services中设置XmlBeans的安装路径。点此下载XmlBeans并解压。

> [下载](http://archive.apache.org/dist/xmlbeans/binaries/xmlbeans-2.6.0.zip)


## 从xsd生成java bean 生成的试个jar 包

> Tools -> XmlBeans -> Generate Java Code From Xml Schema Using XmlBeans。它生成的是jar文件

# XMLActions 实现对xml文档的相关操作