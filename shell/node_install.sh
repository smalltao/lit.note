#±àÒëºÃµÄ
#wget https://nodejs.org/dist/latest-v8.x/node-v8.4.0-linux-x64.tar.gz
#Ô´Âë
#wget -P . https://nodejs.org/dist/v6.11.3/node-v6.11.3.tar.gz
#tar -zxvf node-v6.11.3.tar.gz
cd node-v6.11.3
./configure --prefix=/usr/local/node/v6.11.3
make && make install
echo "export NODE_HOME=/usr/local/node/v6.11.3" >> /etc/profile
echo "export PATH=$NODE_HOME/bin:$PATH" >> /etc/profile
source /etc/profile
node -v
echo $PATH
