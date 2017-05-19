1. 安装npm 
2. 安装gitbook 脚手架 
```
npm install -g gitbook-cli
```bash
3. 查看gitbook 是否安装 
```
gitbook -V
```bash

# 使用
## 根据目录生成图书结构

> `README.md` 相当于本书的简介
```
# mkdir lit.note
# touch README.md
```bash

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
```bash

整理自
http://blog.csdn.net/xiaocainiaoshangxiao/article/details/46882921


