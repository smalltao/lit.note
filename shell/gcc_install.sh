wget http://gcc.skazkaforyou.com/releases/gcc-4.8.2/gcc-4.8.2.tar.gz;
tar -zxvf gcc-4.8.2.tar.gz
cd gcc-4.8.2
mkdir build 
cd build
yum install gmp-devel mpfr-devel libmpc-devel
../configure --prefix=/usr
make && make install
