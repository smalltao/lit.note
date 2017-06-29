## 在linux上使用的markdown编辑器

1. [Atom](https://atom.io/) 可以运行在Linux 、Windows、MAC OS X 系统上
2. [idea Markdown Navigator](https://github.com/vsch/idea-multimarkdown#document-with-pleasure) idea markdown 插件，需要破解
3. markdown 生成邮件 http://m2m.wvv8oo.com/markdown-to-mail.html

## markdown 和 word 互转

> 使用 Writage 插件实现 互转 [下载](https://link.zhihu.com/?target=http%3A//www.writage.com/)

1. 下载

> Writage  https://link.zhihu.com/?target=http%3A//www.writage.com/

> Pandoc https://github.com/jgm/pandoc/releases/tag/1.19.2.1

2. 安装
```
# Writage，word插件
# Pandoc，文档转换后台软件
```

2. 使用

> 安装Writage和Pandoc软件后，word中不会直接出现选项卡，但在【打开】和【保存】的对话框中会出现相关的选项，如下：

### markdown转换word
> 通过word软件打开markdown文件实现：打开原markdown文档后，另存为word格式即可；
> word转换markdown 首先设置word文档中的标准样式，如一级、二级标题等，如此才能与markdown的格式对应；word格式另存为markdown；


## 其他
> pandoc -f markdown -t docx ./test.md -o test.docx
> GitHub风格markdown语法：pandoc -f markdown_github -t docx ./test.md -o test.docx


