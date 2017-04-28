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
```
























    
    