1、vim 配色
	vim 配色文件位置：/usr/share/vim 
	cd ~ 
	vim .vrimrc
	增加配置：  
    set tabstop=4
    set softtabstop=4
    set shiftwidth=4
    set autoindent
    set cindent
    set cinoptions={0,1s,t0,n-2,p2s,(03s,=.5s,>1s,=1s,:1s
    set expandtab
    
    ls 查看/usr/share/vim 下配色文件
    colorscheme github github配色方案 白底黑字 http://www.vim.org/scripts/script.php%3Fscript_id%3D2648
    colorscheme darkburn github配色方案 黑底 http://www.vim.org/scripts/script.php%3Fscript_id%3D2648


2、xshell 下vim 配色方案
    设置256配色 set t_Co=256
3、基本配置
    filetype on 开启文本类型侦测
    filetype plugin on 根据侦测到的不同文件类型加载不同的插件
    
4、移动当前行到指定行 m move n 移动行标为m的行到n行