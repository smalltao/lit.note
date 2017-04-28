// 使nodejs 可以执行shell 命令
var exec = require("child_process").exec;

function start(response) {
    console.log("request handler start was called");

    exec("ls -alh",function(error, stdout, stderr) {
            response.writeHead(200, {"Content-Type":"text/plain"});
            response.write(stdout);
            response.end();
    });
}

function upload(response) { 
    console.log("request handler upload was called");
    response.writeHead(200, {"Content-Type":"text/plain"});
    response.write("hello upload");
    response.end();
}
function dictionary(response) {
    console.log("request handler dictionary was called");
    response.writeHead(200, {"Content-type":"text/html;charset=utf-8"});
    //response.write("{\"code\":0,\"message\":\"OK\"}");
    response.write("{\"code\":403,\"message\":\"OK\"}");
    response.end();
}



exports.start = start;
exports.upload = upload;
exports.dictionary = dictionary;
