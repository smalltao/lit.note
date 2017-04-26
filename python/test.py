#基本
#coding=utf-8
import MySQLdb

conn = MySQLdb.connect(
            host='',
            port='',
            user='',
            passwd='',
            db='',
            )
cur = conn.cursor()

#操作
#获得表中有多少条数据
aa=cur.execute("select * from 7953_pcpvuvcl")
print aa
#打印表中有多少条数据
info=cur.fetchmany(aa)
for ii in info:
    print ii
cur.close()
conn.commit()
conn.close()
