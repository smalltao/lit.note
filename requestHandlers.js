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
    response.write("{\"_type\": \"DictionaryWords\", \"instrumentation\": {\"pingUrlBase\": \"https:\\/\\/cn.bing.com\\/api\\/ping?IG=BCC82C06EA24420B9F88E01DED7430E0&CID=24009C048C6A639A006C966A8DCB62F8&ID=\", \"pageLoadPingUrl\": \"https:\\/\\/cn.bing.com\\/api\\/ping\\/pageload?IG=BCC82C06EA24420B9F88E01DED7430E0&CID=24009C048C6A639A006C966A8DCB62F8&Type=Event.CPT&DATA=0\"}, \"value\": [{\"name\": \"fa\", \"potentialAction\": [{\"name\": \"更多例句\", \"url\": \"http:\\/\\/www.bing.com\\/dict\\/search?q=fa&FORM=BDVSP2\"}, {\"name\": \"下载App\", \"url\": \"http:\\/\\/bing.msn.cn\\/dict\\/\"}], \"pronunciation\": \"fɑ,.ef 'e?\", \"pronunciationAudio\": {\"contentUrl\": \"https:\\/\\/dictionary.blob.core.chinacloudapi.cn\\/media\\/audio\\/tom\\/ae\\/a8\\/AEA8868C2BBFA8F938FD94906C9A7CA9.mp3\"}, \"meaningGroups\": [{\"meanings\": [{\"richDefinitions\": [{\"fragments\": [{\"text\": \"同“fine arts”\"}]}]}], \"partsOfSpeech\": [{\"name\": \"abbr\"}]}, {\"meanings\": [{\"richDefinitions\": [{\"fragments\": [{\"text\": \"【乐】全音阶的长音阶第四音\"}]}]}], \"partsOfSpeech\": [{\"name\": \"名词\"}]}, {\"meanings\": [{\"richDefinitions\": [{\"fragments\": [{\"text\": \"工厂自动化(Factory Automation)\"}, {\"text\": \"叶酸(folic acid)\"}, {\"text\": \"黄腐酸(Fulvic Acid)\"}]}]}], \"partsOfSpeech\": [{\"name\": \"网络\"}]}, {\"meanings\": [{\"richDefinitions\": [{\"fragments\": [{\"text\": \"复数：fas\"}]}]}], \"partsOfSpeech\": [{\"name\": \"变形\"}]}, {\"meanings\": [{\"richDefinitions\": [], \"examples\": [\"Thus, treatment of a Russian Podzol Bh horizon with cold dilute HCI solution resulted in the dissolution of large quantities\", \"所以，在用冷的稀HCI处理苏联灰壤Bh层时造成大量FA的溶失。\"]}], \"partsOfSpeech\": [{\"name\": \"例句\"}]}]}]}");
    response.end();
}



exports.start = start;
exports.upload = upload;
exports.dictionary = dictionary;
