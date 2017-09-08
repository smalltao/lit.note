rsync -avz guest@nginx01.fy.sjs.ted:/usr/local/nginx /usr/local/ --exclude '*_log*'
rsync -avz guest@nginx01.fy.sjs.ted:/search/odin/nginx /search/odin/ --exclude '*_log*'
rsync -avz guest@nginx01.fy.sjs.ted:/etc/init.d/nginx /etc/init.d/
