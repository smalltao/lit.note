1.安装xcode命令行管理工具 安装之后可以在terminal 中使用如git perl make 等命令
    xcode-select --install    look for /Library/Developer/CommandLineTools/
2.包管理工具 HomeBrew
    ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
    紧接着，我们需要做一件事让通过 Homebrew 安装的程序的启动链接 (在 /usr/local/bin中）可以直接运行，无需将完整路径写出。通过以下命令将 /usr/local/bin 添加至 $PATH 环境变量中:
    $ echo 'export PATH="/usr/local/bin:$PATH"' >> ~/.bash_profile
    Cmd+T 打开一个新的 terminal 标签页，运行以下命令，确保 brew 运行正常。
    $ brew doctor
    
    基本使用:
    安装一个包：
        brew install <package_name>
    更新brew在服务器上的包目录：
        brew update
    查看你的包是否需要更新
        brew outdated
    更新包：
        brew upgrade
    清理旧版本
        brew cleanup
    查看你安装过的包列表
        brew list --versions

    homebrew cask 管理图形界面工具
    安装：
    $ brew install caskroom/cask/brew-cask
    $ brew cask install google-chrome // 安装google浏览器
    $ brew update && brew upgrade brew-cask && brew cleanup

    查找需要的app，在caskroom.io 搜索 

    文件预览插件 
        $ brew cask install qlcolorcode #语法高亮
        $ brew cask install qlstephen #预览没有后缀名的文本文件
        $ brew cask install qlmarkdown #预览markdown文件
        $ brew cask install qlquicklook-json #预览json文件
        其他
