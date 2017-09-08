wget -P /search/odin/ https://cache.ruby-lang.org/pub/ruby/2.4/ruby-2.4.1.tar.gz
tar -zxvf ruby-2.4.1.tar.gz
cd ruby-2.4.1
./configure --prefix=/usr/local/ruby-2.4.1
make && make install
echo "PATH=$PATH:/usr/local/ruby-2.4.1/bin;export PATH" >> /etc/profile
source /etc/profile
ruby -v
