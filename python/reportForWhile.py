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

minDate = 20170331
today = 20170425
yesterday = "2017-04-25"
db = DBClass(base='ultraman_report_data_formal', user='readonly', password='readonly', host='10.134.80.202')
while minDate<=today:
    pcout = db.select('7953_pcpvuvcl', 'pv', 'uv', date=today)
    wapout = db.select('7953_wappvuvcl', 'pv', 'uv', date=today)
    pcpv = json.loads(json.dumps(pcout[0]))['pv']
    pcuv = json.loads(json.dumps(pcout[0]))['uv']
    wappv = json.loads(json.dumps(wapout[0]))['pv']
    wapuv = json.loads(json.dumps(wapout[0]))['uv']
    payload = {'xmId': '2002', 'xmKey': 'ae14bf194f10913b','date':yesterday, '217':pcuv, '216':pcpv, '219':wapuv, '218':wappv}
    #url = 'http://10.134.85.224:8888/dictionary'

    s = requests.Session()
    request_retry = HTTPAdapter(max_retries=3)
    s.mount('https://',request_retry)
    s.mount('http://',request_retry)

    r = requests.put(url, payload, timeout=3000)

    print r.url
    print r.text

    today= today-1
    yesterdaystr = str(today)
    yesterdaystr1 = yesterdaystr[0:4]
    yesterdaystr2 = yesterdaystr[4:6]
    yesterdaystr3 = yesterdaystr[6:8]
    yesterday = yesterdaystr1 + "-" + yesterdaystr2 + "-" + yesterdaystr3
    print yesterday






