1、从远程仓库克隆
	git clone https://github.com/smalltao/lit.note.git
	这种方式需要每次输入用户名和密码
	git clone git@github.com:smalltao/lit.note.git
	这种方式每次不需要输入用户名和密码

2、修改系统编码
	vim /etc/sysconfig/i18n
	修改：
	LANG="en_US"
	SYSFONT="latarcyrheb-sun16"
	成:
	LANG="zh_CN.GBK"
	SUPPORTED="zh_CN.UTF-8:zh_CN:zh"
	SYSFONT="latarcyrheb-sun16" 
