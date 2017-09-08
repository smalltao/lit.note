wget -P /search/odin/ https://www.python.org/ftp/python/2.7.9/Python-2.7.9.tar.xz
tar -xvJf Python-2.7.9.tar.xz
cd Python-2.7.9
./configure --prefix=/usr/local/python27
make && make install
