# redis ʵս�ʼ� 
1. ��װ
```
����: wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/redis/redis-2.6.14.tar.gz
��ѹ��tar -xzvf redis-2.6.14.tar.gz 
���룺# make
������# src/redis-server
�� redis ��Ϊ linux �����������
# vim /etc/rc.local�ļ���ĩβ׷�� /root/4setup/redis-2.6.14/src/redis-server
��֤�� �¿�session ����src/redis-cli 
ֹͣ��src/redis-cli shutdown
```bash

2. �������ݿ�
```
���룺
# set name wwl
��ѯ��
# get name 
ɾ��:
del name  ɾ���ɹ���: 1 ɾ��ʧ����: 0 
��֤���Ƿ���ڣ�
exists name �����ǣ�1 �������ǣ�0
```bash
















