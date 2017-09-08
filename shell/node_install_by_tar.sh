mv node-v8.4.0-linux-x64 /usr/local/node/v8.4.0
echo "export NODE_HOME=/usr/local/node/v8.4.0" >> /etc/profile
echo "export PATH=$NODE_HOME/bin:$PATH" >> /etc/profile
source /etc/profile
