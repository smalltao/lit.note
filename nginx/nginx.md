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
    /ͨ��ƥ�䣬

    


    
    
























    
    