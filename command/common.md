常用命令
1、获取指定日期cost  
grep '\[2017-04-20.*\]' stdout.log.201704 | grep 'cost=[1-9]\{4,\}' | sed -n '{s/.*,cost=/cost=/;s/,url=.*/ /p}' 
