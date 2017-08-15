# jekyllcn 静态网页生成器

## 目录
* [官网地址](http://jekyllcn.com/docs/usage/)
* [基本用法](#basic)
* [安装](#install)
* [目录结构](#structure)

<a id="basic"></a>
### 基本用法

构建
```
$ jekyll build
# => 当前文件夹中的内容将会生成到 ./_site 文件夹中。

$ jekyll build --destination <destination>
# => 当前文件夹中的内容将会生成到目标文件夹<destination>中

$ jekyll build --source <source> --destination <destination>
# => 指定源文件夹<source>中的内容将会生成到目标文件夹<destination>中。

$ jekyll build --watch
# => 当前文件夹中的内容将会生成到 ./_site 文件夹中，
#    查看改变，并且自动再生成。
```

浏览

```
$ jekyll serve
# => 一个开发服务器将会运行在 http://localhost:4000/
# Auto-regeneration（自动再生成文件）: 开启。使用 `--no-watch` 来关闭。

$ jekyll serve --detach
# => 功能和`jekyll serve`命令相同，但是会脱离终端在后台运行。
#    如果你想关闭服务器，可以使用`kill -9 1234`命令，"1234" 是进程号（PID）。
#    如果你找不到进程号，那么就用`ps aux | grep jekyll`命令来查看，然后关闭服务器。[更多](http://unixhelp.ed.ac.uk/shell/jobz5.html).

$ jekyll serve --no-watch
# => 和 `jekyll serve` 一样，但不会监测变化。
```

配置代替命令

```
_config.yml
    source:      _source
    destination: _deploy
等价
$ jekyll build
$ jekyll build --source _source --destination _deploy
```

离线文档

```
$ 安装 jekyll-docs
$ 查看 jekyll docs
```

<a id="install"></a>
### 安装

window

```
$ gem install jekyll
```

mac

```
$ Xcode -> Preferences -> Downloads -> Components -> Command-Line Tools
```

[附加功能](http://jekyllcn.com/docs/extras/)
- 数学表达式
- 代码高亮

版本
```
$ jekyll --version
$ gem list jekyll
查找软件包
$ gem search jekyll --remote
更新
$ gem update jekyll
```

<a id="structure"></a>
### 目录结构

```
.
├── _config.yml
├── _drafts
|   ├── begin-with-the-crazy-ideas.textile
|   └── on-simplicity-in-technology.markdown
├── _includes
|   ├── footer.html
|   └── header.html
├── _layouts
|   ├── default.html
|   └── post.html
├── _posts
|   ├── 2007-10-29-why-every-programmer-should-play-nethack.textile
|   └── 2009-04-26-barcamp-boston-4-roundup.textile
├── _site
├── .jekyll-metadata
└── index.html
```

