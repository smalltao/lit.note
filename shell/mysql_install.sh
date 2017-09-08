rpm -qa|grep mysql
rpm -e mysql
#rpm -e --nodeps mysql
groupadd mysql
useradd -r -g mysql mysql
mkdir -p /data/mysql
mkdir -p /usr/local/mysql
tar -zxvf mysql-5.5.13.tar.gz
#yum -y install make gcc-c++ cmake bison-devel ncurses-devel
yum -y install make gcc-c++ 
tar -zxvf cmake-2.8.3.tar.gz
cd /search/odin/cmake-2.8.3
./configure --prefix=/usr/local/cmake
make && make install
yum -y install bison-devel ncurses-devel
cd /search/odin/mysql-5.5.13
cmake \
-DCMAKE_INSTALL_PREFIX=/usr/local/mysql \
-DMYSQL_DATADIR=/data/mysql \
-DSYSCONFDIR=/etc \
-DWITH_MYISAM_STORAGE_ENGINE=1 \
-DWITH_INNOBASE_STORAGE_ENGINE=1 \
-DWITH_MEMORY_STORAGE_ENGINE=1 \
-DWITH_READLINE=1 \
-DMYSQL_UNIX_ADDR=/var/lib/mysql/mysql.sock \
-DMYSQL_TCP_PORT=3306 \
-DENABLED_LOCAL_INFILE=1 \
-DWITH_PARTITION_STORAGE_ENGINE=1 \
-DEXTRA_CHARSETS=all \
-DDEFAULT_CHARSET=utf8 \
-DDEFAULT_COLLATION=utf8_general_ci
#��װ
make && make install
#�޸�mysqlȨ��
cd /usr/local/mysql/
chown mysql.mysql -R .
chown mysql -R /data/mysql
#����mysql
cd /usr/local/mysql/
cp /support-files/my-medium.cnf /etc/my.cnf
#����ִ��Ȩ��
chmod 755 scripts/mysql_install_db
#��ʼ��
cd /usr/local/mysql/
scripts/mysql_install_db --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql/
#����������
cp support-files/mysql.server /etc/init.d/mysqld
chmod 755 /etc/init.d/mysqld
chkconfig mysqld on
#����
service mysqld start
#��ʼ������Ա����
/usr/local/mysql/bin/mysqladmin -u root password '123456'
#����Զ�̵�¼
/usr/local/mysql/bin/mysql -u root -p 123456
# use mysql;
# delete from user where password='';
# flush privileges;
# grant all privileges on *.* to root@'%' identified by "123456";
# flush privileges;
# select User, Password, Host from user;
# quit;
