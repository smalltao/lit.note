# gitbook 笔记
## 相关链接 http://www.chengweiyang.cn/gitbook/github-pages/README.html
## 一步一步的学习
1. 安装npm
2. 安装gitbook 脚手架 

```
npm install -g gitbook-cli
```

3. 查看gitbook 是否安装 

```
gitbook -V
```

# 使用
## 根据目录生成图书结构

> `README.md` 相当于本书的简介

```
# mkdir lit.note
# touch README.md
```

> `SUMMARY.md` 相当于书的目录结构

```
# touch SUMMARY.md
# vim SUMMARY.md
输入
* [简介](README.md)
* [第一章](chapter1/README.md)
 - [第一节](chapter1/section1.md)
 - [第二节](chapter1/section2.md)
* [第二章](chapter2/README.md)
 - [第一节](chapter2/section1.md)
 - [第二节](chapter2/section2.md)
* [结束](end/README.md)
```
```
$ gitbook inti
$ tree . 查看建立的目录和结构
```
## 生成静态网站
### 本地预览时自动生成
```
$ gitbook serve .
```
###  生成到指定目录
```
$ gitbook build --output=./build
```
``` 
$ gitbook fetch 2.6.7 #安装 2.6.7 版本的gitbook   
$ gitbook -v 2.6.7 
$ gitbook uninstall [version] # 卸载指定版本的gitbook
$ gitbook serve ./ --log=debug --debug 启动时打印日志
```
### 生成pdf
> 安装 npm pdf 插件

```
$ npm install phantomjs
$ npm install gitbook-pdf -g
```
> 手动安装 pdf插件

```
$ linux 需要自己编译 phantomjs文件
$ git clone git://github.com/ariya/phantomjs.git
$ windows 直接下载 http://phantomjs.org/download.html 并设置到系统变量path里
$ 下载ebook-convert 直接下载 http://www.calibre-ebook.com/download_windows http://download.calibre-ebook.com/2.85.1/calibre-2.85.1.msi
安装之后将目录设置到path中
$ gitbook pdf .   需要新开一个terminal
```




