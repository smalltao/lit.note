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
```
























    
    