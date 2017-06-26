#!/usr/bin/env python
import MySQLdb
import json
import requests
import datetime
import time
from MySQLdb.cursors import DictCursor
from requests.adapters import HTTPAdapter

class DBClass:
    def __init__(self, **kwargs):
        self.base = kwargs.get('base')
        self.user = kwargs.get('user')
        self.password = kwargs.get('password')
        self.host = kwargs.get('host')

        self.db = MySQLdb.connect(host=self.host,
                             user=self.user,
                             passwd=self.password,
                             db=self.base,
                             cursorclass=DictCursor)

        self.cursor = self.db.cursor()

    def select(self, table, *args, **kwargs):
        """
        
        """
        # what values need select
        selected = '*' if not args else ', '.join([arg for arg in args])
        
        sql = 'SELECT {selected} FROM {table}'.format(table=table, selected=selected)
        
        if kwargs:
        # format kwargs into sql format
            where = ['{key}="{value}"'.format(key=key, value=value) for key, value in kwargs.iteritems()]
            where = ' WHERE ' +' AND '.join(where)
            sql += where           
        print sql
        self.cursor.execute(sql)

        return self.cursor.fetchall()

global flag
flag = False

today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
yesterday1 = yesterday.strftime('%Y%m%d')
yesterday2 = yesterday.strftime('%Y-%m-%d')
print yesterday1
print yesterday2

db = DBClass(base='ultraman_report_data_formal', user='readonly', password='readonly', host='10.134.80.202')
pcout = db.select('7953_pcpvuvcl', 'pv', 'uv', date=yesterday1)
wapout = db.select('7953_wappvuvcl', 'pv', 'uv', date=yesterday1)

if not pcout:
    flag = True

while flag:
    #Dormant minute
    time.sleep(180)
    print 'the pv uv is null reget'
    pcout = db.select('7953_pcpvuvcl', 'pv', 'uv', date=yesterday1)
    wapout = db.select('7953_wappvuvcl', 'pv', 'uv', date=yesterday1)
    if not pcout:
        flag = True
    else:
        flag = False

print pcout
print wapout

pcpv = json.loads(json.dumps(pcout[0]))['pv']
pcuv = json.loads(json.dumps(pcout[0]))['uv']
wappv = json.loads(json.dumps(wapout[0]))['pv']
wapuv = json.loads(json.dumps(wapout[0]))['uv']
payload = {'xmId': '2002', 'xmKey': 'ae14bf194f10913b','date':yesterday2, '217':pcuv, '216':pcpv, '219':wapuv, '218':wappv}
url = 'com.http://10.134.85.224:8888/dictionary'

s = requests.Session()
request_retry = HTTPAdapter(max_retries=3)
s.mount('https://',request_retry)
s.mount('com.http://',request_retry)

r = requests.put(url, payload, timeout=3000)

print r.url
print r.text
#If the return status code 200 is not successful, indicating that the receiving side has problems
print r.status_code

global reportflag
reportflag = False
jsontext = json.loads(r.text)
code = jsontext['code']
message = jsontext['message']
if code!=0:
    reportflag = True

while reportflag:
    #Dormant minute
    time.sleep(180)
    r = requests.put(url, payload, timeout=3000)
    if code!=0:
        reportflag = True
    else:
        reportflag = False
    print r.url
    print r.text





