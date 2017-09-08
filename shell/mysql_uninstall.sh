userdel -r mysql
groupdel mysql
rm -rf /data/mysql
rm -rf /usr/local/mysql
rpm -e --nodeps remove make gcc-c++ cmake bison-devel ncurses-devel
