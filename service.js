var http = require("http");
var url = require("url");
var querystring = require("querystring")

function start(route, handle){
    function onRequest(request,response){
        var postData = "";
        var pathname = url.parse(request.url).pathname;
        //解析get请求
        var query = url.parse(request.url).query;
        var data = querystring.parse(query);

        console.log("request "+ pathname  +"  recevied");

        console.log("xmId is " + data['xmId']);
        console.log("xmKey is " + data['xmKey']);
        console.log("date is " + data['date']);
        console.log("uv is " + data['1']);
        console.log("uv is " + data['2']);

        request.setEncoding("utf8");        
        
        //接收数据块赋值到postdata
        request.addListener("data", function(postDataTrunk) {
            postData += postDataTrunk;

        });

        request.addListener("end", function(){
            // 接收数据， 回调
            console.log(postData);
        })


        route(handle, pathname, response);

        //将response 函数放到route 可以实现非阻塞操作
        //response.writeHead(200, {"Content-Type":"text/plain"});
        //response.write(content);
        //response.end();
    }
    http.createServer(onRequest).listen(8888);
    console.log("service has started");
    //创建服务之后执行
}
exports.start = start;
