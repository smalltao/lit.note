var service = require("./service");
var router = require("./router");
var requestHandlers = require("./requestHandlers");

handle = {};
handle["/"] = requestHandlers.start;
handle["/start"] = requestHandlers.start;
handle["/upload"] = requestHandlers.upload;
handle["/dictionary"] = requestHandlers.dictionary;

service.start(router.route, handle);
