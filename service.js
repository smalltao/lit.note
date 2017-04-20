var http = require("http");
var url = require("url");

function start(route, handle){
    function onRequest(request,response){
        // 事件驱动，只有请求发生时才执行
        var pathname = url.parse(request.url).pathname;
        console.log("request "+ pathname  +"  recevied");
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
