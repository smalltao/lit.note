# git 笔记
## 安装
linux 安装：
	sudo apt-get install git
	
mac 安装：
	安装xcode >> Preferences >> Downloads >> Command Line Tools

window  安装
	msysgit >> https://git-for-windows.github.io
### 使用命令行创建git仓库
```
echo "# index" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/smalltao/index.git
git push -u origin master
```
### 推送已经存在的仓库到github
```
git remote add origin https://github.com/smalltao/index.git
git push -u origin master
```

## 全局配置
	git config --global user.name "your name"
	git config --global user.email "email@example.com"
	config --global 参数表示这台机器所有git仓库都使用这个配置



