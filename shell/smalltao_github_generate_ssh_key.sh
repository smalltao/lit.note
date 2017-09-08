# list all exist keys
ls -al ~/.ssh
ssh-keygen -t rsa -b 4096 -C "731334268@qq.com"
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub > /dev/clipboard
