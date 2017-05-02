nginx 从入门到精通 运维生存时间 http://www.ttlsa.com/nginx/nginx-stu-pdf/
#安装
1、必要软件
安装pcre
安装prce是为了支持重写功能

    \# yum install pcre* 
安装openssl 
需要ssl的支持，如果不需要ssl支持，可以跳过这一步

    \# yum install openssl*
2、安装nginx

    \# ./configure --prefix=/usr/local/nginx-1.5.1 \
    --with-http_ssl_module --with-http_spdy_module \
    --with-http_stub_status_module --with-pcre 
 > --with-http_stub_status_module 支持nginx状态查询
 > --with-http_ssl_module 支持 https
 > --with-http_spdy_module 支持google的spdy 
 > --with-pcre 支持rewrite重写功能
安装make 
    
    \# make insatll
3、启动、关闭、重启nginx
    
    启动
    \# /usr/local/nginx-1.5.1/sbin/nginx
    关闭
    \# /usr/local/nginx-1.5.1/sbin/nginx -s stop
    重新载入配置
    \# /usr/local/nginx-1.5.1/sbin/nginx -s reload
    
## nginx 编译参数详解
```
--prefix=指向安装目录
--sbin-path 指向（执行文件）程序文件（nginx）
--conf-path=指向nginx配置文件（nginx.conf）
--error-log-path=指向错误日志目录
--pid-path=指向pid文件（nginx.pid）
--lock-pth=指向lock文件（nginx.lock)（安装文件锁定，防止安装文件被别人利用，或自己误操作）
--user=指定程序运行时的非特权用户
--group=指定程序运行时的非特权用户组
--builddir=指向编译目录
--with-trsig_module启用rtsig模块支持（实时信号）
--with-select_module 启用select模块支持（一种轮询模式，不推荐在搞负载环境下使用）禁用： --without-selet_module
--with-poll_module 启用poll模块支持（功能与select相同，与select特性相同，为一种轮询模式，不推荐高负载环境使用）
--with-file-aio 启用file aio 支持 （一种apl文件传输格式）
--with-ipv6 启用ipv6支持
--with-http_ssl_module 启用ngx_http_ssl_module 支持 （使支持https请求，需已经安装openssl）
--with-http_realip_module 启用ngx_http_realip_module 支持（这个模块允许从请求头更改客户端的ip地址，默认为关）
--with-http_addition_module 启用ngx_http_addition_module 支持 （作为一个输出过滤器，支持不完全缓存，分部分响应请求）
--with-http_xslt_module 启用ngx_http_xslt_module 支持（过滤转换xml请求）
--with-http_image_filter_module 启用ngx_http_image_filter_module支持 （传输jpeg/gif/png 图片的一个过滤器）（默认为不启用，gd库要用到）
--with-http_geoip_module 启用ngx_http_geoip_module 支持 （该模块创建基于MaxMind GeoIP 二进制相配的客户端ip地址的ngx_http_geoip_module 变量） 
--with-http_sub_module 启用ngx-http_sub_module 支持 （允许用一些其他文本替换nginx响应中的一些文本）
--with-http_dav_module 启用ngx-http_dav_module 支持 （增加PUT、DELETE、MKCOL:创建集合，COPY和MOVE方法）默认情况下关闭，需编译开启
--with-http_flv_moduel 启用ngx-http_flv_module支持 （提供寻求内存使用基于时间的偏移量文件）
--with-http_gzip_static_module 启用ngx_http_gzip_static_module支持（在线实时压缩输出数据流）
--with-http_random_index_module 启用ngx-http_random_index_module （从目录中随机挑选一个目录索引）
--with-http_secure_link_moduel 启用ngx-http_secure_link_module支持 （计算和检查所需要的安全连接网址）
--with-http_degradation_module 启用ngx-http_degradation_module （允许在内存不足的情况下返回204 或444码）
--with-http_stub_status_module 启用ngx-http_stub_status_module支持 （获取nginx自上次启动以来的工作状态）
--without-http_charset_moduel 禁用ngx_http_charset_module 支持 （重新编译web页面，但只能是一个方向 - 服务端到客户端，并且只有一个字节的编码可以被重新编码）
--without-http_gzip_module 禁用ngx_http_gzip_module 支持 （该模块同--with-http_gzip_static_module 功能一样）
--without-http_userid_module 禁用ngx_http_userid_module 支持 （该模块用来处理确定客户端后续请求的cookies）
--without-http_access_module 禁用ngx_http_access_module 支持 （该模块提供了一个简单的基于主机的访问控制，允许/拒绝基于ip地址）
--without-http_auth_basic_module 禁用ngx_http_auth_basic_module 支持 （该模块是可以是用户名和密码基于http基本认证方法来保护你的站点或其部分内容）
--without-http_autoindex_module 禁用 ngx_htt_autoindex_moduel 支持 （该模块用于自动生成目录列表，只在ngx_http_index_module 模块未找到索引文件时发出请求）
--with-http_geo_module 禁用ngx_http_geo_module 支持 （创建一些变量，其值依赖于客户端的IP地址 ）
--with-http_map_module 禁用ngx_http_map_module 支持 （使用任意的键值对设置配置变量）
--without-http_split_clients_module 禁用ngx_http_split_clients_module 支持 （该模块用来基于某些条件划分用户，条件如：IP地址、报头、cookies等等）
--without-http_referer_module 禁用ngx_http_referer_module 支持 （该模块用来过滤请求，拒绝包头中Referer值不正确的请求）
--without-http_rewrier_module 禁用ngx_http_rewrite_module 支持 （该模块允许使用正则表达式改变URI,并且根据变量来转向以及选择配置，如果在server级别设置该选项，那么他们将在location之前生效，如果在location还有更进一步的重写规则，location部分的规则依然会被执行。如果这个URI重写是因为location部分规则造成的，那么location 部分会再次被执行作为新的URI。这个循环会执行10次 ，然后nginx会返回一个500 错误 ）
--without-http_proxy_module 禁用 ngx_http_proxy_module 支持 （有关代理配置）
--without-http_fastcgi_module 禁用ngx_http_fastcgi_module 支持 （该模块允许nginx语fastcgi进程交互，并通过传递参数来控制fastcgi 进程工作，fastcgi 是一个常驻型的公共网关接口）
--without-http_uwsgi_module 禁用ngx_uwsgi_module 支持（改模块用来使用 uwsgi 协议 ，uwsgi 服务器相关）
--without-http_scgi_module 禁用ngx_http_scgi_module 支持 （该模块用来启用scgi协议支持， scgi协议是cgi协议的替代，它是一种应用程序与http服务接口标准）
--without-http_memcached_module 禁用ngx_http_memcached_module支持 （该模块用来提供简单的缓存，以提高系统效率）
--without-http_limit_zone_module 禁用ngx_http_limit_zone_module 支持 （该模块可以针对条件，进行会话的并发链接数限制）
--without-http_limit_req_module 禁用ngx_http_limit_req_module 支持 （该模块允许你对一个地址进行请求数量的限制 用一个给定的session 或一个特定的事件）
--without-http_empty_git_module 禁用ngx_http_empty_git_module 支持 （该模块在内存中常驻一个1*1的透明gif图像，可以被非常快速的调用）
--without-http_browser_module 禁用ngx_http_browser_module 支持 （该模块用来创建依赖于请求报头的值，如果浏览器为modern ，$modern_browser 等于modern_browser 指令分配的值：如果浏览器为old，则$ancient_browser等于 ancient_browser_value指令分配的值 ：如果浏览器我MSIE中的任意版本，则$msie 等于1）
--without-http_upstream_ip_hash_module 禁用ngx_http_upstream_ip_hash_module 支持 （该模块用于简单的负载均衡）
--without-http_perl_module 启用ngx-http_perl_module 支持 （该模式使nginx可以直接使用perl或通过ssi调用perl）
--with-perl_modules_path= 设定perl模块路径
--with_perl=设定perl库文件路径
--http-log-path=设定access log 路径
--http-client-body-temp-path=设定http客户端请求临时文件路径
--http-proxy-temp-path=设定http代理临时文件路径
--http-fastcgi-temp-path=设定http fastcgi 临时文件路径
--http-uwsgi-temp-path=设定http uwsgi 临时文件路径
--http-scgi-temp-path=设定scgi 临时文件路径
--without-http 禁用http server 功能
--without-http-cache 禁用http 缓存功能
--without-mail 启用pop3/imap4/smtp 代理模块支持
--without-mail_ssl_module 启用ngx_mail_ssl_module 支持
--without-mail_pop3_module 禁用pop3 协议 （POP3即邮局协议的第3个版本,它是规定个人计算机如何连接到互联网上的邮件服务器进行收发邮件的协议。是因特网电子邮件的第一个离线协议标 准,POP3协议允许用户从服务器上把邮件存储到本地主机上,同时根据客户端的操作删除或保存在邮件服务器上的邮件。POP3协议是TCP/IP协议族中的一员，主要用于 支持使用客户端远程管理在服务器上的电子邮件）
--without-mail_imap_module 禁用imap 协议（一种邮件获取协议。它的主要作用是邮件客户端可以通过这种协议从邮件服务器上获取邮件的信息，下载邮件等。IMAP协议运行在TCP/IP协议之上， 使用的端口是143。它与POP3协议的主要区别是用户可以不用把所有的邮件全部下载，可以通过客户端直接对服务器上的邮件进行操作。）
--without_mail_smtp_module 禁用smtp 协议 （SMTP即简单邮件传输协议,它是一组用于由源地址到目的地址传送邮件的规则，由它来控制信件的中转方式。SMTP协议属于TCP/IP协议族，它帮助每台计算机在发送或中转信件时找到下一个目的地。）
--with-google_perftools_module 启用ngx_google_perftools_module 支持 （调试用，剖析程序性能瓶颈）
--with-cpp_test_module 启用ngx_cpp_test_module 支持
--add-module= 启用外部模块支持
--with-cc= 执向c编译器路径
--with-cpp= 执向c预处理路径
--with-cc-opt= 设置c编译器参数（pcre库，需要指定 -with-cc-opt="-I /usr/local/include" ，如果使用select()函数则需要同时增加文件描述符数量，可以通过–with-cc- opt=”-D FD_SETSIZE=2048”指定。）
–with-ld-opt= 设置连接文件参数。（PCRE库，需要指定–with-ld-opt=”-L /usr/local/lib”。） 
–with-cpu-opt= 指定编译的CPU，可用的值为: pentium, pentiumpro, pentium3, pentium4, athlon, opteron, amd64, sparc32, sparc64, ppc64 
–without-pcre 禁用pcre库 
–with-pcre 启用pcre库 
–with-pcre= 指向pcre库文件目录 
–with-pcre-opt= 在编译时为pcre库设置附加参数 
–with-md5= 指向md5库文件目录（消息摘要算法第五版，用以提供消息的完整性保护） 
–with-md5-opt= 在编译时为md5库设置附加参数 
–with-md5-asm 使用md5汇编源 
–with-sha1= 指向sha1库目录（数字签名算法，主要用于数字签名） 
–with-sha1-opt= 在编译时为sha1库设置附加参数 
–with-sha1-asm 使用sha1汇编源 
–with-zlib= 指向zlib库目录 
–with-zlib-opt= 在编译时为zlib设置附加参数
–with-md5-asm 使用md5汇编源 
–with-sha1= 指向sha1库目录（数字签名算法，主要用于数字签名） 
–with-sha1-opt= 在编译时为sha1库设置附加参数 
–with-sha1-asm 使用sha1汇编源 
–with-zlib= 指向zlib库目录 
–with-zlib-opt= 在编译时为zlib设置附加参数 
–with-zlib-asm= 为指定的CPU使用zlib汇编源进行优化，CPU类型为pentium, pentiumpro –with-libatomic 为原子内存的更新操作的实现提供一个架构 
–with-libatomic= 指向libatomic_ops安装目录 
–with-openssl= 指向openssl安装目录 
–with-openssl-opt 在编译时为openssl设置附加参数 
–with-debug 启用debug日志
```

## nginx安装配置，清缓存模块安装

1、下载软件包

    # mkdir /usr/local/src/tarbag
    # mkdir /usr/local/src/software
    # cd /usr/local/src/tarbag
    Nginx 
    # wget http://nginx.org/download/nginx-1.0.6.tar.gz
    # Nginx cache purge 模块 （可选）
    # wget http://labs.frickle.com/files/ngx_cache_purge-1.3.tar.gz

2、编译安装

    # cd /usr/local/src/tarbag
    # tar -xzvf nginx-1.0.6.tar.gz -C /usr/local/src/software  #解压到当前目录下的software目录下
    # tar -xzvf ngx_cache_purge-1.3.tar.gz -C /usr/local/src/software 
    # cd /usr/local/src/software/
    # ./configure \ 
    -prefix=/usr/local/nginx-1.0.6 \ # 安装路径
    -with-http_stub_status_module \ # 启用nginx状态模块
    --with-http_ssl_module \ # 启用ssl 模块
    --with-http_realip_module \ #　启用realip模块（将用户ip转发给后端服务器）
    -add-module=../ngx_cache_purge-1.3 # 添加缓存清除扩展模块
    #make 
    #make install
    
3、内核参数优化
    
    #vim sysctl.conf 增加以下配置
    # vi sysctl.conf 增加以下配置 
    net.ipv4.netfilter.ip_conntrack_tcp_timeout_established = 1800 
    net.ipv4.ip_conntrack_max = 16777216 ＃ 如果使用默认参数,容易出现网络丢包 
    net.ipv4.netfilter.ip_conntrack_max = 16777216＃ 如果使用默认参数,容易出现网络丢包 
    net.ipv4.tcp_max_syn_backlog = 65536 
    net.core.netdev_max_backlog = 32768 
    net.core.somaxconn = 32768 
    net.core.wmem_default = 8388608 
    net.core.rmem_default = 8388608 
    net.core.rmem_max = 16777216 
    net.core.wmem_max = 16777216 
    net.ipv4.tcp_timestamps = 0 
    net.ipv4.tcp_synack_retries = 2 
    net.ipv4.tcp_syn_retries = 
    net.ipv4.tcp_tw_recycle = 1 
    net.ipv4.tcp_tw_reuse = 1 
    net.ipv4.tcp_mem = 94500000 915000000 927000000 
    net.ipv4.tcp_max_orphans = 3276800 
    net.ipv4.ip_local_port_range = 1024 65535
    配置生效
    #sysctl -p
    修改iptables 启用脚本，在star()函数里面加上
    # vim /etc/init.d/iptables
    /sbin/sysctl -p 

4、配置范例：站点
    
    序号  域名  目录
    1   www.tools.com   /www/html/www.tools.com
    2   bbs.tools.com   /www/html/bbs.tools.com

5、修改nginx配置文件
    
    #vim nginx.conf
    user nobody nobody; #运行nginx的所有组和所有者
    worker_processes 2; #开启两个nginx工作进程，一般几个cpu核心就写几个
    error_log logs/error.log notice; #错误日志路径
    pid logs/nginx.pid; #pid路径
    events {
        worker_connections 1024; #一个进程能同时处理1024个请求
    }
    http {
        include mime.types;
        default_type application/octet-stream;
        
        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
        '$status $body_bytes_sent "$http_refoerer" '
        '"$http_user_agent" "$http_x_forwarded_for" '
        access_log logs/access.log main; # 默认访问日志路径
        sendfile on;
        keeplive_timeout 65; #keeplive 超时时间（长连接超时时间）
        
        # 开始配置一个域名，一个server配置段 一段对应一个域名
        server {
            listen 80; # 在本机所有ip上监听80 ，也可以写192.168.1.201:80,这样的话就只监听192.168.1.201的80 端口
            server_name www.tools.com; # 域名
            root /www/html/www.test.com; # 站点根目录（程序目录）
            index index.html index.htm; # 索引文件
            location / { # 可以有多个location
                root /www.html/www.test.com; # 站点根目录（程序目录）
                error_page 500 502 503 504 /50x.html;
                #定义错误页面，如果是500错误，则把站点根目录下的50x.html返给用户
                location = /50x.html {
                    root /www/html/www.tools.com;
                }
            }
        }
        #开始站点bbs配置
        server {
            listen 80; # 在本机所有ip上监听80 ，也可以写192.168.1.201:80,这样的话就只监听192.168.1.201的80 端口
            server_name bbs.tools.com; # 域名
            root /www/html/www.test.com; # 站点根目录（程序目录）
            index index.html index.htm; # 索引文件
            location / { # 可以有多个location
                root /www.html/www.test.com; # 站点根目录（程序目录）
                error_page 500 502 503 504 /50x.html;
                #定义错误页面，如果是500错误，则把站点根目录下的50x.html返给用户
                location = /50x.html {
                    root /www/html/bbs.tools.com;
                }
            }
        }
    } 
    
6、nginx启动关闭
    
    # /usr/local/nginx-1.0.6/sbin/nginx # 启动nginx
    # /usr/local/nginx-1.0.6/sbing/nginx -t # 测试nginx配置文件的准确性
    # /usr/local/nginx-1.0.6/sbing/nginx -s reload # 重载nginx
    # /usr/local/nginx-1.0.6/sbing/nginx -s stop # 关闭nginx

7、测试、创建测试站点
    
    # mkdir -p /www/html/www.tools.com
    # mkdir -p /www/html/bbs.tools.com
    # echo "www.tools.com" > /www.html/www.tools.com/index.html
    # echo "www.bbs.tools.com" > /www.html/bbs.tools.com/index.html
8、启动nginx
    
    # /usr/local/nginx-1.0.6/sbin/nginx -t # 看到ok和successful 说明配置文件没有问题
    # /usr/local/nginx-1.0.6/sbin/nginx
    
9、绑定host、测试

    把两个域名指向192.168.1.201
    192.168.1.201   www.tools.com
    192.168.1.201   bbs.tools.com

10、location 语法规则
    
    语法规则：location [=|~|~*|^~] /uri/ { ... }
    = 表示精确匹配，优先级最高
    ^~ 表示url以某个常规字符串开头，理解为url路径即可，nginx不对路径做编码，因此/static/20%/aa 可以被规则 ^~ /static/ /aa匹配到（20% 是空格）
    ~ 表示区分大小写的正则匹配
    ~* 表示不分区大小写的正则匹配
    !~ 和 !~* 分别为区分大小写和不区分大小写的不匹配正则
    /通用匹配，

    


    
    
























    
    