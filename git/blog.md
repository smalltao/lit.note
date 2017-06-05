# 使用gitpage + hexo 搭建博客
## 安装nodejs git hexo

### 安装hexo
```
$ cd D:\work\svn\self\lit.note\blog>
$ npm install hexo-cli -g
$ hexo init blog
$ cd blog
$ npm install
$ hexo g #hexo generate
$ hexo s #hexo server http://localhost:4000/
```

1. hexo generate (hexo g) 生成静态文件，会在当前目录下生成一个新的叫做public的文件夹
2. hexo server (hexo s) 启动本地web服务，用于博客的预览
3. hexo deploy (hexo d) 部署播客到远端（比如github, heroku等平台）
4. hexo new "postName" #创建新文章
5. hexo new page "pageName" #新建页面

> 简写：
```
$ hexo n == hexo new
$ hexo g == hexo generate
$ hexo s == hexo server
$ hexo d == hexo deploy
组合：
$ hexo d -g #生成部署
$ hexo s -g #生成预览
```

> 查看本地环境
```
$ hexo -v
```

## 主题
### 安装 `yilia` 主题
```
$ hexo clean
$ git clone https://github.com/litten/hexo-theme-yilia.git themes/yilia
```
### 启用主题
> 修改Hexo目录下_config.yml 配置文件中的theme属性，将其设置为yilia

> 更新主题

```
$ cd themes/yilia
$ git pull
$ hexo g # 生成
$ hexo s # 启动
```

## github pages 设置
> 每个帐号只能有一个仓库来存放个人主页，而且仓库的名字必须是username/username.github.io，这是特殊的命名约定。你可以通过http://username.github.io 来访问你的个人主页。
> 这里特别提醒一下，需要注意的个人主页的网站内容是在master分支下的。

## 部署 `Hexo` 到 `Github Pages`
这一步恐怕是最关键的一步了，让我们把在本地web环境下预览到的博客部署到github上，
然后就可以直接通过http://jiji262.github.io/访问了。不过很多教程文章对这个步骤语焉不详，这里着重说下。

首先需要明白所谓部署到github的原理。

之前步骤中在Github上创建的那个特别的repo（jiji262.github.io）一个最大的特点就是其master中的html静态文件，可以通过链接http://jiji262.github.io来直接访问。
Hexo -g 会生成一个静态网站（第一次会生成一个public目录），这个静态文件可以直接访问。
需要将hexo生成的静态网站，提交(git commit)到github上。
明白了原理，怎么做自然就清晰了


