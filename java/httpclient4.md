# httpclient 4 官方向导 整理 
> 这是读 《南磊的 httpclient 4 官方翻译》 的读书笔记 

## 基础 
### 执行请求
```
HttpClient httpclient = new DefaultHttpClient();
HttpGet httpget = new HttpGet("http://localhost/");
HttpResponse response = httpclient.execute(httpget);
HttpEntity entity = response.getEntity();
if (entity != null) {
    InputStream instream = entity.getContent();
    int l;
    byte[] tmp = new byte[2048];
    while ((l = instream.read(tmp)) != -1) {
    }
}
HttpGet httpget = new HttpGet("http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
URI uri = URIUtils.createURI("http", "www.google.com", -1, "/search","q=httpclient&btnG=Google+Search&aq=f&oq=", null);
HttpGet httpget = new HttpGet(uri);
System.out.println(httpget.getURI());
```
1. HttpClient支持所有定义在HTTP/1.1版本中的方法： GET，HEAD， POST，PUT，DELETE，TRACE，OPTIONS
2. URI协议模式：主机名称，可选的端口，资源路径，可选的查询，可选的片段
> 查询的字符串也可以从独立的参数中生成 
```
List<NameValuePair> qparams = new ArrayList<NameValuePair>();
qparams.add(new BasicNameValuePair("q", "httpclient"));
qparams.add(new BasicNameValuePair("btnG", "Google Search"));
qparams.add(new BasicNameValuePair("aq", "f"));
qparams.add(new BasicNameValuePair("oq", null));
URI uri = URIUtils.createURI("http", "www.google.com", -1, "/search",
URLEncodedUtils.format(qparams, "UTF-8"), null);
HttpGet httpget = new HttpGet(uri);
System.out.println(httpget.getURI());
```
### 响应
> 响应报文第一行包含了协议版本，之后的数字状态码，和相关连的文本段

#### 处理报文头部
```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK, "OK");
System.out.println(response.getProtocolVersion());
System.out.println(response.getStatusLine().getStatusCode());
System.out.println(response.getStatusLine().getReasonPhrase());
System.out.println(response.getStatusLine().toString());
```
> HttpClient 提供获取、添加、删除、枚举报文头部的方法




