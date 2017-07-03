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

###  pandoc 使用
官方命令demo：http://pandoc.org/try/

```
pandoc x.html -o x.md
pandoc -f html -t markdown http://www.fsf.org
pandoc --from markdown_strict --to html5

# -f 参数用于指定源文件格式
# -t 参数用于指定输出文件格式
# -o 参数用于指定输出文件
# --ascii 可以避免转成utf-8编码，这样中文在浏览器上就不会乱码了

pandoc --latex-engine=xelatex yourfile.txt -o newfile.pdf
注意，为了正确转换中文文本，请修改模板文件，在模板文件第一行下方加入
\usepackage{ctex}
当然你首先得确认你的系统上可以正确编译和生成中文tex文件。另外，模板文件在哪里呢？这个需要说明一下，你可以使用如下命令查看：
pandoc -D latex
```
> 如果不使用-f和-t参数，pandoc将会根据输入文件以及-o指定的输出文件格式来确定转换的格式类型

> Pandoc不支持.doc格式，如果需要进行转换，则需要先将.doc转换为.docx

#### 样式控制
```
--css 控制生成 html 的样式
--reference-docx 控制生成 docx 的样式
```
> 使用 template，可以先用 pandoc -D latex 看默认的模板，然后修改一下保存成template.latex，然后用 pandoc 的时候加上 --template template.latex 选项，就可以了。


