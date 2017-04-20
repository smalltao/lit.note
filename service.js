var http = require("http");

function start(){
    function onRequest(request,response){
       ponse.writeHead(200, {"Content-Type":"text/plain"});
       response.write("hello world");
       response.end();
    }
    http.createServer(onRequest).listen(8888);
}
exports.start = start;
