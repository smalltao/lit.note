nginx �����ŵ���ͨ ��ά����ʱ�� http://www.ttlsa.com/nginx/nginx-stu-pdf/
#��װ
1����Ҫ���
��װpcre
��װprce��Ϊ��֧����д����

    \# yum install pcre* 
��װopenssl 
��Ҫssl��֧�֣��������Ҫssl֧�֣�����������һ��

    \# yum install openssl*
2����װnginx

    \# ./configure --prefix=/usr/local/nginx-1.5.1 \
    --with-http_ssl_module --with-http_spdy_module \
    --with-http_stub_status_module --with-pcre 
 > --with-http_stub_status_module ֧��nginx״̬��ѯ
 > --with-http_ssl_module ֧�� https
 > --with-http_spdy_module ֧��google��spdy 
 > --with-pcre ֧��rewrite��д����
��װmake 
    
    \# make insatll
3���������رա�����nginx
    
    ����
    \# /usr/local/nginx-1.5.1/sbin/nginx
    �ر�
    \# /usr/local/nginx-1.5.1/sbin/nginx -s stop
    ������������
    \# /usr/local/nginx-1.5.1/sbin/nginx -s reload
    
## nginx ����������
```
--prefix=ָ��װĿ¼
--sbin-path ָ��ִ���ļ��������ļ���nginx��
--conf-path=ָ��nginx�����ļ���nginx.conf��
--error-log-path=ָ�������־Ŀ¼
--pid-path=ָ��pid�ļ���nginx.pid��
--lock-pth=ָ��lock�ļ���nginx.lock)����װ�ļ���������ֹ��װ�ļ����������ã����Լ��������
--user=ָ����������ʱ�ķ���Ȩ�û�
--group=ָ����������ʱ�ķ���Ȩ�û���
--builddir=ָ�����Ŀ¼
--with-trsig_module����rtsigģ��֧�֣�ʵʱ�źţ�
--with-select_module ����selectģ��֧�֣�һ����ѯģʽ�����Ƽ��ڸ㸺�ػ�����ʹ�ã����ã� --without-selet_module
--with-poll_module ����pollģ��֧�֣�������select��ͬ����select������ͬ��Ϊһ����ѯģʽ�����Ƽ��߸��ػ���ʹ�ã�
--with-file-aio ����file aio ֧�� ��һ��apl�ļ������ʽ��
--with-ipv6 ����ipv6֧��
--with-http_ssl_module ����ngx_http_ssl_module ֧�� ��ʹ֧��https�������Ѿ���װopenssl��
--with-http_realip_module ����ngx_http_realip_module ֧�֣����ģ�����������ͷ���Ŀͻ��˵�ip��ַ��Ĭ��Ϊ�أ�
--with-http_addition_module ����ngx_http_addition_module ֧�� ����Ϊһ�������������֧�ֲ���ȫ���棬�ֲ�����Ӧ����
--with-http_xslt_module ����ngx_http_xslt_module ֧�֣�����ת��xml����
--with-http_image_filter_module ����ngx_http_image_filter_module֧�� ������jpeg/gif/png ͼƬ��һ������������Ĭ��Ϊ�����ã�gd��Ҫ�õ���
--with-http_geoip_module ����ngx_http_geoip_module ֧�� ����ģ�鴴������MaxMind GeoIP ����������Ŀͻ���ip��ַ��ngx_http_geoip_module ������ 
--with-http_sub_module ����ngx-http_sub_module ֧�� ��������һЩ�����ı��滻nginx��Ӧ�е�һЩ�ı���
--with-http_dav_module ����ngx-http_dav_module ֧�� ������PUT��DELETE��MKCOL:�������ϣ�COPY��MOVE������Ĭ������¹رգ�����뿪��
--with-http_flv_moduel ����ngx-http_flv_module֧�� ���ṩѰ���ڴ�ʹ�û���ʱ���ƫ�����ļ���
--with-http_gzip_static_module ����ngx_http_gzip_static_module֧�֣�����ʵʱѹ�������������
--with-http_random_index_module ����ngx-http_random_index_module ����Ŀ¼�������ѡһ��Ŀ¼������
--with-http_secure_link_moduel ����ngx-http_secure_link_module֧�� ������ͼ������Ҫ�İ�ȫ������ַ��
--with-http_degradation_module ����ngx-http_degradation_module ���������ڴ治�������·���204 ��444�룩
--with-http_stub_status_module ����ngx-http_stub_status_module֧�� ����ȡnginx���ϴ����������Ĺ���״̬��
--without-http_charset_moduel ����ngx_http_charset_module ֧�� �����±���webҳ�棬��ֻ����һ������ - ����˵��ͻ��ˣ�����ֻ��һ���ֽڵı�����Ա����±��룩
--without-http_gzip_module ����ngx_http_gzip_module ֧�� ����ģ��ͬ--with-http_gzip_static_module ����һ����
--without-http_userid_module ����ngx_http_userid_module ֧�� ����ģ����������ȷ���ͻ��˺��������cookies��
--without-http_access_module ����ngx_http_access_module ֧�� ����ģ���ṩ��һ���򵥵Ļ��������ķ��ʿ��ƣ�����/�ܾ�����ip��ַ��
--without-http_auth_basic_module ����ngx_http_auth_basic_module ֧�� ����ģ���ǿ������û������������http������֤�������������վ����䲿�����ݣ�
--without-http_autoindex_module ���� ngx_htt_autoindex_moduel ֧�� ����ģ�������Զ�����Ŀ¼�б�ֻ��ngx_http_index_module ģ��δ�ҵ������ļ�ʱ��������
--with-http_geo_module ����ngx_http_geo_module ֧�� ������һЩ��������ֵ�����ڿͻ��˵�IP��ַ ��
--with-http_map_module ����ngx_http_map_module ֧�� ��ʹ������ļ�ֵ���������ñ�����
--without-http_split_clients_module ����ngx_http_split_clients_module ֧�� ����ģ����������ĳЩ���������û��������磺IP��ַ����ͷ��cookies�ȵȣ�
--without-http_referer_module ����ngx_http_referer_module ֧�� ����ģ�������������󣬾ܾ���ͷ��Refererֵ����ȷ������
--without-http_rewrier_module ����ngx_http_rewrite_module ֧�� ����ģ������ʹ��������ʽ�ı�URI,���Ҹ��ݱ�����ת���Լ�ѡ�����ã������server�������ø�ѡ���ô���ǽ���location֮ǰ��Ч�������location���и���һ������д����location���ֵĹ�����Ȼ�ᱻִ�С�������URI��д����Ϊlocation���ֹ�����ɵģ���ôlocation ���ֻ��ٴα�ִ����Ϊ�µ�URI�����ѭ����ִ��10�� ��Ȼ��nginx�᷵��һ��500 ���� ��
--without-http_proxy_module ���� ngx_http_proxy_module ֧�� ���йش������ã�
--without-http_fastcgi_module ����ngx_http_fastcgi_module ֧�� ����ģ������nginx��fastcgi���̽�������ͨ�����ݲ���������fastcgi ���̹�����fastcgi ��һ����פ�͵Ĺ������ؽӿڣ�
--without-http_uwsgi_module ����ngx_uwsgi_module ֧�֣���ģ������ʹ�� uwsgi Э�� ��uwsgi ��������أ�
--without-http_scgi_module ����ngx_http_scgi_module ֧�� ����ģ����������scgiЭ��֧�֣� scgiЭ����cgiЭ������������һ��Ӧ�ó�����http����ӿڱ�׼��
--without-http_memcached_module ����ngx_http_memcached_module֧�� ����ģ�������ṩ�򵥵Ļ��棬�����ϵͳЧ�ʣ�
--without-http_limit_zone_module ����ngx_http_limit_zone_module ֧�� ����ģ�����������������лỰ�Ĳ������������ƣ�
--without-http_limit_req_module ����ngx_http_limit_req_module ֧�� ����ģ���������һ����ַ������������������ ��һ��������session ��һ���ض����¼���
--without-http_empty_git_module ����ngx_http_empty_git_module ֧�� ����ģ�����ڴ��г�פһ��1*1��͸��gifͼ�񣬿��Ա��ǳ����ٵĵ��ã�
--without-http_browser_module ����ngx_http_browser_module ֧�� ����ģ��������������������ͷ��ֵ����������Ϊmodern ��$modern_browser ����modern_browser ָ������ֵ����������Ϊold����$ancient_browser���� ancient_browser_valueָ������ֵ ������������MSIE�е�����汾����$msie ����1��
--without-http_upstream_ip_hash_module ����ngx_http_upstream_ip_hash_module ֧�� ����ģ�����ڼ򵥵ĸ��ؾ��⣩
--without-http_perl_module ����ngx-http_perl_module ֧�� ����ģʽʹnginx����ֱ��ʹ��perl��ͨ��ssi����perl��
--with-perl_modules_path= �趨perlģ��·��
--with_perl=�趨perl���ļ�·��
--http-log-path=�趨access log ·��
--http-client-body-temp-path=�趨http�ͻ���������ʱ�ļ�·��
--http-proxy-temp-path=�趨http������ʱ�ļ�·��
--http-fastcgi-temp-path=�趨http fastcgi ��ʱ�ļ�·��
--http-uwsgi-temp-path=�趨http uwsgi ��ʱ�ļ�·��
--http-scgi-temp-path=�趨scgi ��ʱ�ļ�·��
--without-http ����http server ����
--without-http-cache ����http ���湦��
--without-mail ����pop3/imap4/smtp ����ģ��֧��
--without-mail_ssl_module ����ngx_mail_ssl_module ֧��
--without-mail_pop3_module ����pop3 Э�� ��POP3���ʾ�Э��ĵ�3���汾,���ǹ涨���˼����������ӵ��������ϵ��ʼ������������շ��ʼ���Э�顣�������������ʼ��ĵ�һ������Э��� ׼,POP3Э�������û��ӷ������ϰ��ʼ��洢������������,ͬʱ���ݿͻ��˵Ĳ���ɾ���򱣴����ʼ��������ϵ��ʼ���POP3Э����TCP/IPЭ�����е�һԱ����Ҫ���� ֧��ʹ�ÿͻ���Զ�̹����ڷ������ϵĵ����ʼ���
--without-mail_imap_module ����imap Э�飨һ���ʼ���ȡЭ�顣������Ҫ�������ʼ��ͻ��˿���ͨ������Э����ʼ��������ϻ�ȡ�ʼ�����Ϣ�������ʼ��ȡ�IMAPЭ��������TCP/IPЭ��֮�ϣ� ʹ�õĶ˿���143������POP3Э�����Ҫ�������û����Բ��ð����е��ʼ�ȫ�����أ�����ͨ���ͻ���ֱ�ӶԷ������ϵ��ʼ����в�������
--without_mail_smtp_module ����smtp Э�� ��SMTP�����ʼ�����Э��,����һ��������Դ��ַ��Ŀ�ĵ�ַ�����ʼ��Ĺ��������������ż�����ת��ʽ��SMTPЭ������TCP/IPЭ���壬������ÿ̨������ڷ��ͻ���ת�ż�ʱ�ҵ���һ��Ŀ�ĵء���
--with-google_perftools_module ����ngx_google_perftools_module ֧�� �������ã�������������ƿ����
--with-cpp_test_module ����ngx_cpp_test_module ֧��
--add-module= �����ⲿģ��֧��
--with-cc= ִ��c������·��
--with-cpp= ִ��cԤ����·��
--with-cc-opt= ����c������������pcre�⣬��Ҫָ�� -with-cc-opt="-I /usr/local/include" �����ʹ��select()��������Ҫͬʱ�����ļ�����������������ͨ���Cwith-cc- opt=��-D FD_SETSIZE=2048��ָ������
�Cwith-ld-opt= ���������ļ���������PCRE�⣬��Ҫָ���Cwith-ld-opt=��-L /usr/local/lib������ 
�Cwith-cpu-opt= ָ�������CPU�����õ�ֵΪ: pentium, pentiumpro, pentium3, pentium4, athlon, opteron, amd64, sparc32, sparc64, ppc64 
�Cwithout-pcre ����pcre�� 
�Cwith-pcre ����pcre�� 
�Cwith-pcre= ָ��pcre���ļ�Ŀ¼ 
�Cwith-pcre-opt= �ڱ���ʱΪpcre�����ø��Ӳ��� 
�Cwith-md5= ָ��md5���ļ�Ŀ¼����ϢժҪ�㷨����棬�����ṩ��Ϣ�������Ա����� 
�Cwith-md5-opt= �ڱ���ʱΪmd5�����ø��Ӳ��� 
�Cwith-md5-asm ʹ��md5���Դ 
�Cwith-sha1= ָ��sha1��Ŀ¼������ǩ���㷨����Ҫ��������ǩ���� 
�Cwith-sha1-opt= �ڱ���ʱΪsha1�����ø��Ӳ��� 
�Cwith-sha1-asm ʹ��sha1���Դ 
�Cwith-zlib= ָ��zlib��Ŀ¼ 
�Cwith-zlib-opt= �ڱ���ʱΪzlib���ø��Ӳ���
�Cwith-md5-asm ʹ��md5���Դ 
�Cwith-sha1= ָ��sha1��Ŀ¼������ǩ���㷨����Ҫ��������ǩ���� 
�Cwith-sha1-opt= �ڱ���ʱΪsha1�����ø��Ӳ��� 
�Cwith-sha1-asm ʹ��sha1���Դ 
�Cwith-zlib= ָ��zlib��Ŀ¼ 
�Cwith-zlib-opt= �ڱ���ʱΪzlib���ø��Ӳ��� 
�Cwith-zlib-asm= Ϊָ����CPUʹ��zlib���Դ�����Ż���CPU����Ϊpentium, pentiumpro �Cwith-libatomic Ϊԭ���ڴ�ĸ��²�����ʵ���ṩһ���ܹ� 
�Cwith-libatomic= ָ��libatomic_ops��װĿ¼ 
�Cwith-openssl= ָ��openssl��װĿ¼ 
�Cwith-openssl-opt �ڱ���ʱΪopenssl���ø��Ӳ��� 
�Cwith-debug ����debug��־
```

## nginx��װ���ã��建��ģ�鰲װ

1�����������

    # mkdir /usr/local/src/tarbag
    # mkdir /usr/local/src/software
    # cd /usr/local/src/tarbag
    Nginx 
    # wget http://nginx.org/download/nginx-1.0.6.tar.gz
    # Nginx cache purge ģ�� ����ѡ��
    # wget http://labs.frickle.com/files/ngx_cache_purge-1.3.tar.gz

2�����밲װ

    # cd /usr/local/src/tarbag
    # tar -xzvf nginx-1.0.6.tar.gz -C /usr/local/src/software  #��ѹ����ǰĿ¼�µ�softwareĿ¼��
    # tar -xzvf ngx_cache_purge-1.3.tar.gz -C /usr/local/src/software 
    # cd /usr/local/src/software/
    # ./configure \ 
    -prefix=/usr/local/nginx-1.0.6 \ # ��װ·��
    -with-http_stub_status_module \ # ����nginx״̬ģ��
    --with-http_ssl_module \ # ����ssl ģ��
    --with-http_realip_module \ #������realipģ�飨���û�ipת������˷�������
    -add-module=../ngx_cache_purge-1.3 # ��ӻ��������չģ��
    #make 
    #make install
    
3���ں˲����Ż�
    
    #vim sysctl.conf ������������
    # vi sysctl.conf ������������ 
    net.ipv4.netfilter.ip_conntrack_tcp_timeout_established = 1800 
    net.ipv4.ip_conntrack_max = 16777216 �� ���ʹ��Ĭ�ϲ���,���׳������綪�� 
    net.ipv4.netfilter.ip_conntrack_max = 16777216�� ���ʹ��Ĭ�ϲ���,���׳������綪�� 
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
    ������Ч
    #sysctl -p
    �޸�iptables ���ýű�����star()�����������
    # vim /etc/init.d/iptables
    /sbin/sysctl -p 

4�����÷�����վ��
    
    ���  ����  Ŀ¼
    1   www.tools.com   /www/html/www.tools.com
    2   bbs.tools.com   /www/html/bbs.tools.com

5���޸�nginx�����ļ�
    
    #vim nginx.conf
    user nobody nobody; #����nginx���������������
    worker_processes 2; #��������nginx�������̣�һ�㼸��cpu���ľ�д����
    error_log logs/error.log notice; #������־·��
    pid logs/nginx.pid; #pid·��
    events {
        worker_connections 1024; #һ��������ͬʱ����1024������
    }
    http {
        include mime.types;
        default_type application/octet-stream;
        
        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
        '$status $body_bytes_sent "$http_refoerer" '
        '"$http_user_agent" "$http_x_forwarded_for" '
        access_log logs/access.log main; # Ĭ�Ϸ�����־·��
        sendfile on;
        keeplive_timeout 65; #keeplive ��ʱʱ�䣨�����ӳ�ʱʱ�䣩
        
        # ��ʼ����һ��������һ��server���ö� һ�ζ�Ӧһ������
        server {
            listen 80; # �ڱ�������ip�ϼ���80 ��Ҳ����д192.168.1.201:80,�����Ļ���ֻ����192.168.1.201��80 �˿�
            server_name www.tools.com; # ����
            root /www/html/www.test.com; # վ���Ŀ¼������Ŀ¼��
            index index.html index.htm; # �����ļ�
            location / { # �����ж��location
                root /www.html/www.test.com; # վ���Ŀ¼������Ŀ¼��
                error_page 500 502 503 504 /50x.html;
                #�������ҳ�棬�����500�������վ���Ŀ¼�µ�50x.html�����û�
                location = /50x.html {
                    root /www/html/www.tools.com;
                }
            }
        }
        #��ʼվ��bbs����
        server {
            listen 80; # �ڱ�������ip�ϼ���80 ��Ҳ����д192.168.1.201:80,�����Ļ���ֻ����192.168.1.201��80 �˿�
            server_name bbs.tools.com; # ����
            root /www/html/www.test.com; # վ���Ŀ¼������Ŀ¼��
            index index.html index.htm; # �����ļ�
            location / { # �����ж��location
                root /www.html/www.test.com; # վ���Ŀ¼������Ŀ¼��
                error_page 500 502 503 504 /50x.html;
                #�������ҳ�棬�����500�������վ���Ŀ¼�µ�50x.html�����û�
                location = /50x.html {
                    root /www/html/bbs.tools.com;
                }
            }
        }
    } 
    
6��nginx�����ر�
    
    # /usr/local/nginx-1.0.6/sbin/nginx # ����nginx
    # /usr/local/nginx-1.0.6/sbing/nginx -t # ����nginx�����ļ���׼ȷ��
    # /usr/local/nginx-1.0.6/sbing/nginx -s reload # ����nginx
    # /usr/local/nginx-1.0.6/sbing/nginx -s stop # �ر�nginx

7�����ԡ���������վ��
    
    # mkdir -p /www/html/www.tools.com
    # mkdir -p /www/html/bbs.tools.com
    # echo "www.tools.com" > /www.html/www.tools.com/index.html
    # echo "www.bbs.tools.com" > /www.html/bbs.tools.com/index.html
8������nginx
    
    # /usr/local/nginx-1.0.6/sbin/nginx -t # ����ok��successful ˵�������ļ�û������
    # /usr/local/nginx-1.0.6/sbin/nginx
    
9����host������

    ����������ָ��192.168.1.201
    192.168.1.201   www.tools.com
    192.168.1.201   bbs.tools.com

10��location �﷨����
    
    �﷨����location [=|~|~*|^~] /uri/ { ... }
    = ��ʾ��ȷƥ�䣬���ȼ����
    ^~ ��ʾurl��ĳ�������ַ�����ͷ�����Ϊurl·�����ɣ�nginx����·�������룬���/static/20%/aa ���Ա����� ^~ /static/ /aaƥ�䵽��20% �ǿո�
    ~ ��ʾ���ִ�Сд������ƥ��
    ~* ��ʾ��������Сд������ƥ��
    !~ �� !~* �ֱ�Ϊ���ִ�Сд�Ͳ����ִ�Сд�Ĳ�ƥ������
    /ͨ��ƥ�䣬�κ����󶼻�ƥ�䵽
    
    ���ȼ�
    ���locaton����˳��
    ���ȼ�  = >^~ > ����ƥ��= ���ƥ��^~ �����������ƥ�䣬���/ ͨ��ƥ�䣬��ƥ��ɹ�ʱֹͣƥ�䣬����ǰ������
    
    location / {
        echo "/"; #��Ҫ��װechoģ��
    }
    
    ��̬�ļ�ƥ�����
    location ~* .*\.(js|ccc)?$ {
        expires 7d; # 7�����
        access_log off; #��������־
    }
    location ~* .*\.(png|jpg|gif|jpeg|bmp|ico)?$ {
        expires 7d;
        access_log off;
    }
    location ~* .*\.(zip|rar|exe|msi|iso|gho|mp3|rmvb|mp4|wma|rm)?$ {
        deny all; # ��ֹ��Щ�ļ�����
    }

11��nginx root & alias �ļ�·������
nginxָ���ļ�·�������ַ�ʽ

    [root]
    �﷨��root path 
    Ĭ��ֵ��root html
    ���öΣ�http��server��location��if
    
    [alias]
    �﷨��alias path
    ���öΣ�location
    
    eg:
    location ~ ^/weblogs/ {
        root /data/weblogs/www.tools.com;
        autoindex on;
        auth_basic "Restricted";
        auth_basic_user_file passwd/weblogs
    }
    ���һ�������url��/weblogs/httplogs/www.tools.com-access.logʱ��web���������᷵�ط��ط�������/data/weblogs/www.tools.com/weblogs/httplogs/www.tools.com-access.log���ļ�
    ����������url����ӳ�䣬Ҳ����/path/uri
    
    location ^~ /binapp/ {
        limit_con limit 4;
        limit_rate 200k;
        internal;
        alias /data/statics/bin/apps/;
    }
    #alias���location�������õ�·�����������ѵ�ǰƥ�䵽��Ŀ¼ָ��ָ����Ŀ¼�����һ�������uri��/binapp/a.tools.com/faviconʱ��web���������᷵��/data/statics/bin/apps/a.tools.com/favicon.jpg���ļ�
    #alias ����ָ���κ�����
    #alias ��ʹ������ƥ��ʱ�����벶׽Ҫƥ������ݲ���ָ�������ݴ�����
    #alias ֻ��λ��location����

12��ngx_http_core_module ��������ʱ�����д����ı�������Щ��������ͨ��������־��¼������Ҳ������������nginxģ��

    ��������    ע��
    $arg_PARAMETER HTTP ������ĳ��������ֵ����/index.php?site=www.tools.com,������$arg_siteȡ��www.tools.com���ֵ
    $args HTTP �����е��������������磺������/index.php?width=400&height=200�У�$args��ʾ�ַ���width=400$height=200
    $binary_remote_addr �����Ƹ�ʽ�Ŀͻ��˵�ַ�����磺\xOA\xEOB\XOE
    $body_tytes_sent ��ʾ��ͻ��˷��͵�http��Ӧ�У����岿�ֵ��ֽ���
    $content_length ��ʾ�ͻ�������ͷ���е�Content-Length�ֶ�
    $content_type ��ʾ�ͻ�������ͷ���е�Content-Type�ֶ�
    $cookie_COOKIE ��ʾ�ͻ�������ͷ���е�cookie�ֶ�
    $document_root ��ʾ��ǰ������ʹ�õ�root�������ֵ
    $uri ��ʾ��ǰ�����uri,�����κβ���
    $document_uri ͬ$uri
    $request_uri ��ʾ�ͻ��˷�����ԭʼ����uri���������Ĳ���,$uri ��document_uriδ�����û���ԭʼ�������ڲ��ض���֮��������ض���֮���uri,��$request_uri��Զ����ı䣬ʼ���ǿͻ��˵�ԭʼuri
    $host ��ʾ�ͻ�������ͷ���е�Host�ֶΣ����Host�����ڣ�����ʵ�ʴ����server���������������ƴ��档���Host�ֶδ��ж˿ڣ��磺IP��PORT����ô$host��ȥ���˿ڣ�����ֵ��ip��$host��ȫСд�ģ���Щ������http_HEADER�е�http_host��ͬ��http_hostֻȡ��Hostͷ����Ӧ�ĵ�ֵ
    $hostname ��ʾnginx���ڻ��������ƣ���gethostbyname ���ص�ֵ��ͬ
    $http_HEADER ��ʾ��ǰHTTP��������Ӧ����ͷ����ֵ��HEADER����ȫСд�����磺������Host ͷ����Ӧ��ֵ $http_host
    $sent_http_HEADER��ʾ���ظ��ͻ�����Ӧ����Ӧͷ����ֵ��HEADER����ȫСд�����磺$sent_http_content_type ��ʾ��Ӧ��Content-Type
    $is_args ��ʾ������uri�Ƿ�����������������,$is_argsֵΪ�������������������ֵΪ���ַ���
    $limit_rate ��ʾ��ǰ���ӵ������Ƕ��٣�0��ʾ������
    $nginx_version ��ʾ��ǰnginx�汾��
    $query_string ����url�еĲ�������$args������ͬ������$query_string ֻ������ı�
    $remote_addr ��ʾ�ͻ��˵ĵ�ַ
    $remote_port ��ʾ�ͻ������ؼ�ʹ�õĶ˿�
    $remote_user ��ʾʹ��Auth Basic Module ʱ������û���
    $request_filename ��ʾ�û������е�url����root��proxy_pass��fastcgi_pass��������
    $request_body ��ʾhttp�����еİ��壬�ò���ֻ��proxy_pass ��fastcai_pass��������
    $request_body_file ��ʾhttp�����а���洢����ʱ�ļ���
    $request_completion �������Ѿ�ȫ�����ʱ����ֵΪok ��û��û��ɣ�����ֵΪ���ַ���
    $request_method ��ʾhttp����ķ���������get��put��post
    $server_addr ��ʾ��������ַ
    $server_name ��ʾ����������
    $server_port ��ʾ�������˿�
    $server_protocol ��ʾ��������ͻ��˷��͵���ӦЭ�飬��HTTP/1.1 ��HTTP/1.0

## nginx��־����

## nginx ��д����
1����д����������pcre�⣬������Ҫ��װpcre��locationѭ�����ִ��10�Σ�����֮�󷵻�500 ������дģ�����setָ����������µı���������ֵ

2��rewite ģ��ָ��
    
    break
    �﷨��break
    Ĭ��ֵ��none
    ʹ���ֶΣ�server; location; if
    
    if 
    �﷨��if (condition) { ... }
    Ĭ��ֵ��none
    ʹ���ֶΣ�server; location
    ע�⣺����ʹ��trp_files����
    �жϵ���������������ֵ
    1��һ�����������ƣ� ���ַ���" "����һЩ"0" ��ʼ���ַ���Ϊfalse
    2���ַ����Ƚϣ�ʹ��=��!=Ԥ���
    3��������ʽ��ʹ��~�����ִ�Сд����~* �������ִ�Сд����ȡ��Ԥ��!~��!~*
    4���ļ��Ƿ���ڣ�ʹ��-f ��!-f������
    5��Ŀ¼�Ƿ����:ʹ��-d ��!-d������
    6���ļ���Ŀ¼�����������Ƿ���ڣ�ʹ��-e��!-e������
    7���ļ��Ƿ��ִ�У�ʹ��-x ��!-x������
    
    return
    �﷨��return code
    Ĭ��ֵ��none
    ʹ���ֶΣ�server; location; if
    ֹͣ����δ�ͻ��˷���״̬�롣�Ǳ�׼��444״̬�뽫�ر����ӣ��������κ���Ӧͷ������ʹ�õ�״̬�룺
    204; 400; 402-406; 408; 410; 411; 413; 416; 500-504;���״̬�븽�����ֶ��䣬���ı�����ֹ����Ӧ���⡣
    �෴�����״̬�������һ��url ����url����Ϊlocationͷ��ֵ��û��״̬���url������Ϊһ��302״̬�롣
    
    rewrite 
    �﷨��rewrite regex replacement flag
    Ĭ��ֵ��none
    ʹ���ֶΣ�server; location; if
    ������ص�������ʽ���ַ����޸�url��ָ����������ļ��г��ֵ�˳��ִ�С���������дָ�������ӱ��
    ע�⣺����滻���ַ�����http://��ͷ�����󽫱��ض��򣬲��Ҳ���ִ�ж����rewirteָ��
    β���ģ�flag������������ֵ��
    last - ֹͣ������дģ��ָ�֮������location ����ĺ��uriƥ��
    break - �����дָ��
    redirect - ����302 ��ʱ�ض�������滻�ֶ���http://��ͷ��ʹ��
    permanent - ����301 �����ض���
    
    rewrite_log
    �﷨��rewrite_log on | off
    Ĭ��ֵ��rewrite_log off
    ʹ���ֶΣ�server; location; if
    ��������
    ����ʱ����error log�м�¼notice �������д��־

    set 
    �﷨�� set variable value
    Ĭ��ֵ��none
    ʹ���ֶΣ�server; location; if 
    Ϊ�����ı�������һ���ض�ֵ
    
    uninitialized_variable_warn
    �﷨��uninitialized_variable_warn on|off
    Ĭ��ֵ��uninitialized_variable_warn on
    ʹ���ֶΣ�http; server; location; if 
    �����Ƿ��¼δ��ʼ�������ľ�����Ϣ
    
2����д������ɲ���
    
    1��������ʽ
    ������ʽ�����ݿ���ͨ�����Ų��񣬺�������ͨ��λ�ý������ã�λ�ñ�����ֵȡ���ڲ���������ʽ��˳��$1���õ�һ�������е����ݣ�$2���õڶ��������е�ֵ���Դ����� eg:
    ^/images/([a-z]{2})/(a-z0-9){5}/(.*)\.(png|jpg|gif)$
    $1������Сд��ĸ��ɵ��ַ���
    $2����Сд��ĸ��0��9��������ɵ�5���ַ����ַ���
    $3���ļ���
    $4��png jpg gif�е�һ��
    
    2��uri
    ��URI���ܰ���������ʽ�в����λ�ò�������������µ�nginx�κ�����eg:
    /data?file=$3.$4
    ������uri��ƥ��nginx���õ��κε�location ,��ô�����ͻ��˷���301�������ض��򣩻�302����ʱ�ض��򣩵�״̬�룬��״̬����ͨ��������������ȷָ��(������������ɶ��)
    
    3��β����ǣ�falg��
    last ��ǽ�������д���uri ����ƥ��nginx������location��������ѭ��10��eg��
    rewrite ^/images/([a-z]{2})/(a-z0-9){5}/(.*)\.(png|jpg|gif)$ /data?file=$3.$4 last;
    break ָ����Ե�������ָ�� eg��
    if ($bwhog) {
        limit_rate 300k;
        break;
    }
    ��һ��ֹͣ��дģ�鴦��ָ����return ����������httpģ�鴦����������ζ�ţ�nginxֱ�ӷ�����Ϣ���ͻ��ˣ���error_page���Ϊ�ͻ��˳��ָ�ʽ����htmlҳ�棬
    ���״̬�븽�����ֶ��䣬���ı�������������Ӧ���壬�෴�����״̬�������һ��url����url����Ϊlocationͷ�Ĳ�ֵ��û��״̬��url������Ϊ302״̬��eg:
    location = /image404.html {
        return 404 "image not found\n";
    }
    
3��ʵ��

        http {
            #����image ��־��ʽ
            log_format imagelog '[$time_local]' '$image_file' '$image_type' '$body_sent' '$status';
            #������д��־
            rewrite_log on;
            
            server {
                root /home/www;
                
                location / {
                    #��д������Ϣ
                    error_log logs/rewrite.log notice;
                    #ע������Ҫ��'' ����������������{}
                    rewrite '^/images/([a-z]{2})/(a-z0-9){5}/(.*)\.(png|jpg|gif)$' /data?file=$3.$4;
                    #ע�ⲻ���������������������ϡ�last�� ���������������setָ���ִ��
                    set $image_file $3;
                    set $image_type $4;
                }
                
                location /data {
                    #ָ�����ͼƬ����־��ʽ��������ͼƬ���ͺʹ�С
                    access_log logs/images.log main;
                    root /data/images;
                    #Ӧ��ǰ��ı����������ж��ļ��治���ڣ��������ж�Ŀ¼�ڲ��ڣ�������ǲ��ھ���ת�����һ��url��
                    try_file/$arg_file/images404.html;
                }
                
                location = /image404.html {
                    #ͼƬ�������򷵻��ض�����Ϣ
                    return 404 "image not found\n";
                }
            }
        }

4�������µ���д����
    
    ʵ��1 Ҫ��homeĿ¼�ض�����ҳ���ϣ�Ŀ¼�ṹ����
    /
    /home
    /home/index
    /home/index/
    /index
    /index.php
    /index.php/
    ��д�������£�
    rewrite ^/(home(/index)?|index(\.php)?)/?$ $schema;
    // $host/ permanent;
    
    
    
    
    


    
    
























    
    