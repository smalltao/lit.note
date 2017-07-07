# wap build
```
# !/bin/sh
usage () {
	echo "Usage : front-build-new -p {web,wap,weixin,zhihu,english_web,mingyi_web}"
	exit 0
}
# 移除rpm包
remove_rpm() {
    yum remove -y resin-xx
    yum remove -y apache-xx
    yum remove -y java
    yum remove -y jdk
}
# 安装jdk
build_jdk() {
    echo "install jdk ..."
    yum install -y java-1.8.0-oracle-devel.x86_64
}
#安装resin
build_resin_1() {
    echo "install resin ..."
    yum -y erase resin*
    yum install -y resin-pro-xx.x86_64
}
#安装resin for wap
buid_resin_2() {
    echo "install resin ..."
    yum -y erase resin*
    yum install -y resin-pro-xx-wap.x86_64
}
build_resin_zhihu(){
	echo "install resin ... "
    yum -y erase resin*
    yum install -y resin-xx.x86_64
}

build_nginx_1() {
    echo "install nginx ..."
    yun install erase nginx*
    yum -y install nginx-xx-search.newpassport.x86_64
}

build_nginx_2(){
	echo "install nginx ... "
    yum -y erase nginx*
	yum -y install nginx-xx-search-wap.x86_64
}

# 构建应用
build_app() {
    # 加载变量
    local pre_online
    . ./pre_online
    if [ -z $web_preonline ] || [ -z $wap_preonline ] || [ -z $weixin_preonline ] || [ -z $zhihu_preonline ] || [ -z $english_web_preonline ] || [ -z $mingyi_web_preonline ];then
        echo "PreOnline Config Error"
        exit 1
    fi
    case $PRODUCT in
        web)
            echo "start to build web environment"
            killall java
            killall nginx

            echo "start build web nginx code ..."
            rm -rf /search/nginx
            rm -rf /search/apache
            rm -rf /search/odin/nginx
            rsync -azi $web_preonline::odin/search/odin/nginx /search/odin/ --exclued '_log*'

            echo "start build web nginx conf ..."
            rm -rf /usr/local/nginx
            rsync -azi $web_preonline::odin/search/usr/local/nginx /usr/local/
            mv /usr/local/nginx/conf/opconf/soso.conf /usr/local/nginx/conf/opconf/soso.conf.bak

            echo "start build web resin code ..."
            rm -rf /search/odin/data
            rsync -azi /$web_preonline::odin/search/odin/data /search/odin/
            rm -rf /search/odin/resin
            mkdir /seearch/odin/resin
            rsync -azi $web_preonline::odin/search/odin/resin/bin /search/odin/resin/
            rsync -azi $web_preonline::odin/search/odin/resin/data /search/odin/resin/
            rsync -azi $web_preonline::odin/search/odin/resin/web /search/odin/resin/
            rsync -azi $web_preonline::odin/search/odin/resin/log /search/odin/resin/ --exclude '*.log.20*'

            echo "start build web resin conf ..."
            rm -rf /usr/local/resin
            rsync -azi $web_preonline::odin/search/usr/local/resin /usr/local/
            rsync -azi $web_preonline::odin/search/usr/lib/liblockmemory.so /usr/lib/
            cp /usr/local/resin/bin/resinctl /etc/init.d/resin
            chmod 777 /etc/init.d/resin

            # alternatives是Linux下的一个功能强大的命令。只能在root权限下执行。如系统中有几个命令功能十分类似，却又不能随意删除，那么可以用 alternatives 来指定一个全局的设置。
            alternatives --set java  /usr/lib/jvm/jre-1.8.0-oracle.x86_64/bin/java
            alternatives --set javac /usr/lib/jvm/java-1.8.0-oracle.x86_64/bin/javac

            service resin start
            service nginx start
            echo "build web environment OK";
        wap)
        english_web)
        mingyi_web)
        weixin)
        zhihu)
        *)
    esac
}

# if [ $a -lt $b ] 意味着如果a的值小于b的值。
# if [ $# -lt 2 ] 意味着位置参数的设置数小于2。 传入参数的值小于2

if [ $# -lt 2 ] ; then
    usage
if
# getopts 语法：getopts options variable
#options 以冒号开头表示屏蔽脚本的系统提示错误，自己处理错误提示。
#后面接合法的单字母选项，选项后若有冒号，则表示该选项必须接具体的参数
#$OPTIND为特殊变量，表示第几个选项，初始值为1
while getopts "p:e" option
do
    case $option in
        p) PRODUCE=${OPTARG};
        e) REMOVE=1;
        ?) echo "error"
            exit 1;
done
# -n product 长度非0
if [ -n $PRODUCT ] ; then
    mkdir -p /search/odin
    if [ -n "$REMOVE" ] && [ $REMOVE -eq 1 ] ; then
        echo "clear up Basic Env ..."
    if

    # -o 如果 shell选项 “OPTIONNAME” 开启则为真。
    build_jdk
    if [ "$PRODUCT" == "wap" ] ; then
		build_resin_2
    	build_nginx_2
    elif [ "$PRODUCT" == "web" -o "$PRODUCT" == "weixin" -o "$PRODUCT" == "mingyi_web" ] ; then
        build_resin_1
        build_nginx_1
    elif [ "$PRODUCT" == "english_web" ] ; then
        build_resin_2
        build_nginx_1
    elif [ "$PRODUCT" == "zhihu" ] ; then
        build_resin_zhihu
        build_nginx_1
    fi
    echo "Start Build App Env ..."
    build_app
    echo "Build App Env End"

    echo "All Build Over ."
fi

```

