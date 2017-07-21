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

```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK")
response.addHeader("Set-Cookie", "cl=a; path=/; domain=localhost");
response.addHeader("Set-Cookie", "c2=b;path=\"/\",c3=3;domain=\"localhsot\"")
Header h1 = resposne.getFirstHeader("Set-Cookie");
System.out.println(h1);
Header h2 = new resposne.getLastHeader("Set-Cookie");
System.out.println(h2);
Header[] hs = response.getHeaders("Set-Cookie")
System.out.println(hs.length);
```

> 输出内容为：
```
Set-Cookie: c1=a; path=/; domain=localhost
Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
```

> 获取给定类型的所有头部信息最有效的方式是使用HeaderIterator接口

```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK")
response.addHeader("Set-Cookie", "cl=a; path=/; domain=localhost");
response.addHeader("Set-Cookie", "c2=b;path=\"/\",c3=3;domain=\"localhsot\"")
HeaderIterator it = response.headerIterator("Set-Cookie");
while (it.hasNext()) {
System.out.println(it.next())
}
```
> 也提供解析HTTP报文到独立头部信息元素的方法

```
HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
while (it.hasNext()) {
System.out.println(elem.getName() + "=" + elem.getValue())
NameValuePair[] params = elem.getParameters();
    for (int i=0;i < params.length; i++) {
        System.out.println(" " + params[i])
    }
}
```
> 输出结果

```
c1 = a
path=/
domain=localhost
c2 = b
path=/
c3 = c
domain=localhost
```

## 实体
> HTTP报文可以携带和请求或响应相关的内容实体。实体可以在一些请求和响应中找到，因为它们也是可选的。使用了实体的请求被称为封闭实体请求。
> HTTP规范定义了两种封闭实体的方法：POST和PUT。响应通常期望包含一个内容实体。这个规则也有特例，比如HEAD方法的响应和204 No Content，304 Not Modified和205 Reset Content响应。
> HttpClient根据其内容出自何处区分三种类型的实体：

1. `streamed` 流式：内容从流中获得，或者运行中产生。特别是这种分类包含从HTTP响应中获取的实体。流式实体是不可重复生成的。
2. `self_contained` 自我包含式： 内容从内存中或通过独立的链接或者其它实体中获得。自我包含式的实体是可以重复生成的。这种类型的实体经常会用户封闭HTTP请求的实体
3. `wrapping` 包装式：内容从另外一个实体中获得

> 当从一个HTTP响应中获取流式内容时，这个区别对于链接管理器很重要。对于由应用程序创建而且使用HttpClient发送的请求实体，流式和自我包含式的不同就不能么重要了，这种情况下，建议考虑如流式这种不能重复的实体，和可以重复的自我包含式实体

### 重复实体
> 实体可以重复，意味着它的内容可以被多次读取。这就仅仅是自我包含式的实体了（像： ByteArrayEntity 、 StringEntity）

### 使用 HTTP 实体
> 要从实体中读取内容，可以通过 HttpEntity#getContent()方法从输入流中获取，这会返回一个java.io.InputStream对象，
或者提供一个输出流到HttpEntity#writeTo(OutputSteam)方法中，这会一次写入返回所有写入到给定流中的内容。
> 当实体通过一个收到的报文获取时，HttpEntity#getContentType()方法和HttpEntity#getContentLength()方法可以用来读取通用的元数据，
如：Content-Type、Content-Length头信息（如果它们是可用的），因为头部信息Content-Type可以包含对本文MIME类型的字符编码，如：text/plain 、text/html,HttpEntity#getContentEncoding() 方法用来获取这个信息

> 当为一个传出报文创建实体时，这个元数据不得不通过实体创建器来提供:

```
StringEntity entity = new StringEntity("important message", "UTF-8")
System.out.println(entity.getContentType());
System.out.println(entity.getContentLength());
System.out.println(EntityUtils.getContentCharSet(entity));
System.out.println(EntityUtils.toString(entity));
System.out.println(EntityUtils.toByteArray(entity).length);
```
> 输出结果：

```
Content-Type: text/plain; charset=UTF-8
17
UTF-8
important message
17
```

### 确保低级别资源释放
> 当完成一个响应实体，那么保证所有实体内容已经被完全消耗是很重要的，所以链接可以安全的返回到连接池中，而且可以通过链接管理器对后续的请求重用链接。
> 处理这个操作的最方便的方法是调用 `HttpEntity#consumeContent()` 方法来消耗流中的任意可用内容。
> HttpClient 探测到内容流尾部已经到达后，会立即自动释放底层链接，并放回到连接管理器。
> HttpEntity#consumeContent()方法调用多次也是安全的
> 也可能会有特殊情况，当整个响应内容的一小部分需要获取，消耗剩余内容而损失性能，还有重用链接的代价太高，可可以仅仅通过调用 HttpUriRequest#abort()方法来终止请求:

```
HttpGet httpget = new HttpGet("http://localhost/")
HttpResposne response = httpclient.execute(httpget);
HttpEntity entity = response.getEntity();
if (entity != null) {
    InputStream instream = entity.getContent();
    int byteOne = instream.read();
    int byteTwo = instream.read();
    //不需要读取以后的内容了
    httpget.abort();
}
```
> 连接不会被重用，但是由它持有的所有级别的资源将会被正确释放。

### 消耗实体内容
> 推荐消耗实体内容的方式是使用它的HttpEntity#getContent()或HttpEntity#wirteTo(OutputStream)方法。
> HttpClient也自带EntityUtils类，这会暴露一些静态方法，这些方法可以更加容易地从实体中读取内容或信息
> 代替直接读取 java.io.InputStream,也可以使用这个类中的方法以字符串/字节数组的形式获取整个内容体
> 然而，EntityUtils的使用是强烈不鼓励的，除非响应实体源自可靠的HTTP服务器和已知的长度限制。

```
HttpGet httpget = new HttpGet("http://localhost/")
HttpResponse response = httpClient.execute(httpget);
HttpEntity entity = response.getEntity();
if (entity != null) {
    long len = entity.getContentLength();
    if (len !=-1 && len < 2048) {
        System.out.println(EntityUtils.toString(entity));
    } else {
        // 流的内容输出
    }
}
```
> 在一些情况下可能不止一次的读取实体。此时实体内容必须以某种方式在内存或磁盘上被缓存起来。
> 最简单的方法是通过使用BufferedHttpEntity类来包装实体类完成。这会引起实体内容被读取到的内存缓存区中，在其他所有方式中，实体包装器将会得到源实体

```
HttpGet httpget = new HttGet("http://localhost/");
HttpResponse response = httpclient.execute(httpget);
HttpEntity entity = response.getEntity();
if (entity != null) {
    entityt = new BufferedHttpEntity(entityt);
}
```

### 生成实体内容
> HttpClient 提供一些类，它们可以用于生成通过HTTP 链接获得内容的有效输出流。
> 为了封闭实体从HTTP 请求中获得的输出内容，哪些类的实体可和封闭如POST和PUT请求的实体相关联。
> HttpClient 为很多的公用数据容器，比如字符串，字节数组，输入流和文件提供了一些类: StringEntity,ByteArrayEntity, InputStreamEntity ,FileEntity

```
File file = new File("somefile.txt");
FileEntity entity = new FileEntity(file, "text/plain; charset= \"UTF-8\"");
HttpPost httppost = new HttpPost("http://localhost/action.do");
httppost.setEntity(entity);
```

> 请注意InputStreamEntity是不可重复的，因为它们仅仅能从底层数据流中读取一次内容。
> 通常来说，我们推荐实现一个定制的HttEntity类，这是自我包含式的，用来代替使用通用的InputSteamEntity。FileEntity也是一个很好的起点。

### 动态内容实体
> 通常来说，HTTP 实体需要基于特定的执行上下文来动态的生成，通过使用EntityTemplate实体类和ContentProducer接口，HttpClient提供了动态实体的支持。
> 内容生成器是按照需求生成他们内容的对象，将它们写入到一个输出流中，他们是每次被请求时来生成内容。所以用EntityTemplated 创建的实体是自我包含而且可以重复的

```
ContentProducer cp = new ContentProducer() {
    public void writeTo(OutputStream outstream) throws IOException {
        Writer writer = new OutputStreamWriter(outstream, "UTF-8");
        writer.write("<resposne>");
        writer.write("content");
        writer.write("important stuff");
        writer.write("</content>");
        writer.write("</response>");
        writer.flush();
    }
}
HttpEntity entity = new EntityTemplate(cp);
HttpPost httppost = new HttpPost("http://localhost/handler.do");
httppost.setEntity(entity);
```

### HTML 表单
> 需要应用需要频繁模拟提交一个HTML 表单的过程，比如，为了记录一个Web应用程序或提交输出数据。HttpClient提供了一些的实体类 UrlEncodeFormEntity 来满足这个过程。

```
List<NameValuePair> formparams = new ArrayList<NameValuePair>();
formparams.add(new BasicNameValuePair("param1", "value1"));
formparams.add(new BasicNameValuePair("param2", "value2"));
UrlEncodeFormEntity entity = new UrlEncodeFormEntity(formparams, "UTF-8");
HttpPost httpost = new HttpPost("http://localhost/handler.do");
httppost.setEntity(entity);
```
> UrlEncodedFormEntity 实体将会使用URL编码来编码参数，生成内容如下：

```
params1=value1&param2=value2
```
### 内容分块
> 通过，我们推荐HttpClient 选择基于被传递的HTTP 报文属性最适合的编码转换。
> 设置HttpEntity#setChuncked()方法为true是通知HttpClient分块编码的首选。
> 请注意HttpClient 将会使用标识作为提示。
> 当使用HTTP 协议版本，如 HTTP/1.0版本，不支持分块编码时，这个值会被忽略。
```
StringEntity entity = new StringEntity("important message"， "text/plain; charset=\"UTF-8\"");
entity.setChuncked(true);
HttpPost httppost = new HttpPost("http://localhost/action.do");
httppost.setEntity(entity);
```
### 响应控制器
> 控制响应最简单和最方便的方式是使用ResposneHandler接口。
> 当使用ResponseHandler时，HttpClient将会自动关注并保证释放连接到连接管理器中去，而不管请求执行是否成功或引发了异常

```
HttpClient httpclient = new DefaultHttpClient();
HttpGet httpget = new HttGet("http://localhost/");
ResposneHandler<byte[]> handler = new ResponseHandler<byte[]>() {
    public byte[] handlerResponse(HttpResposne response) throws ClientProtocolException, IOException {
        HttpEtity entity = resposne.getEntity();
        if (entity != null) {
            return EntityUtils.toByteArray(entity);
        } else {
            return null;
        }
    }
}
byte[] response = httpclient.execute(Httpget, handler);
```

### HTTP 执行环境
>　最初，HTTP 是被设计成无状态的，面向请求-响应的协议。然而，真实的应用程序经常需要提供一些逻辑相关的请求-响应交换来持久状态信息
> 为了开启应用程序来维持一个过程状态，HttpClient允许HTTP请求在一个特定的执行环境中来执行，简称为HTTP上下问。
> 如果相同的环境在连续请求之间重用，那么多种逻辑相关的请求可以参与到一个逻辑会话中。HTTP 上下问功能和java.util.Map<String,Object>很相似。
> 它仅仅是任意命名参数值的集合。应用程序可以在请求之前或在检查上下文执行完成之后来填充上下文属性。
> 在HTTP请求执行的这一过程中，HttpClient添加了下列属性到执行上下文中：

1. http.connection : HttpConnection 实例代表了链接到目标服务器的真实链接
2. http.target_host : HttpHost 实例代表了链接目标
3. http.proxy_host : 如果使用了，HttpHost实例代表了代理链接
4. http.request ：HttpRequest 实例代表了真实的HTTP请求
5. http.response : HttpResponse 实例代表饿了真实的HTTP响应
6. http.request_sent : java.lang.Boolean 对象代表了暗示真实请求是否被完全传递到目标链接的标识

> 比如，为了决定最终的重定向目标，在请求执行之后，可以检查http.target_host属性的值

```
DefaultHttpClient httpclient = new DefaultHttpClient();
HttpContext localContext = new BasicHttpContext();
HttpGet httpget = new HttpGet("http://www.google.com/");
HttpResponse response = httpclient.execute(httpget, localContext);
HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST)
System.out.println("Final target: " + target);
HttpEntity entity = response.getEntity();
if (entity != null) {
    entity.consumeContent();
}
```
> 输出结果:

```
Final target: http://www.google.ch
```

## 异常处理

> HttpClient能够抛出两种类型的异常：在I/O失败时，如套接字连接超时或被重置的java.io.IOException异常，还有标志HTTP请求失败的信号，如违反HTTP协议的HttpException异常。
> 通常I/O错误被认为是非致命的和可以恢复的，而HTTP协议错误则被认为是致命的而且是不能自动恢复的。

## HTTP运输安全

> 要理解HTTP协议并不是对所有类型的应用程序都适合的，这一点很重要。HTTP是一个简单的面向请求/响应的协议，最初被设计用来支持取回静态或动态生成的内容。
它从未向支持事务性操作方向发展。比如，如果成功收到和处理请求，HTTP服务器将会考虑它的其中一部分是否完成，生成一个响应并发送一个状态码到客户端。如果客户端因为读取超时，
请求取消或系统崩溃导致接收响应实体失败时，服务器不会试图回滚事务。如果客户端决定重新这个请求，那么服务器将不可避免地不止一次执行这个相同的事务。
在一些情况下，这会导致应用数据损坏或者不一致的应用程序状态。
尽管HTTP从来都没有被设计来支持事务性处理，但它也能被用作于一个传输协议对关键的任务应用提供被满足的确定状态。要保证HTTP传输层的安全，系统必须保证HTTP方法在应用层的幂等性。

## 幂等的方法

> HTTP/1.1 明确地定义了幂等的方法，描述如下
[方法也可以有“幂等”属性在那些（除了错误或过期问题）N的副作用>0的相同请求和独立的请求是相同的]
换句话说，应用程序应该保证准备着来处理多个相同方法执行的实现。这是可以达到的，比如，通过提供一个独立的事务ID和其它避免执行相同逻辑操作的方法。
请注意这个问题对于HttpClient是不具体的。基于应用的浏览器特别受和非幂等的HTTP方法相关的相同问题的限制。
HttpClient假设没有实体包含方法，比如GET和HEAD是幂等的，而实体包含方法，比如POST和PUT则不是。

## 异常自动恢复

>默认情况下，HttpClient会试图自动从I/O异常中恢复。默认的自动恢复机制是受很少一部分已知的异常是安全的这个限制。

1. HttpClient不会从任意逻辑或HTTP协议错误（那些是从HttpException类中派生出的）中恢复的。
2. HttpClient将会自动重新执行那么假设是幂等的方法。
3. HttpClient将会自动重新执行那些由于运输异常失败，而HTTP请求仍然被传送到目标服务器（也就是请求没有完全被送到服务器）失败的方法。
4. HttpClient将会自动重新执行那些已经完全被送到服务器，但是服务器使用HTTP状态码（服务器仅仅丢掉连接而不会发回任何东西）响应时失败的方法。

>在这种情况下，假设请求没有被服务器处理，而应用程序的状态也没有改变。
如果这个假设可能对于你应用程序的目标Web服务器来说不正确，那么就强烈建议提供一个自定义的异常处理器。

## 请求重试处理
> 为了开启自定义异常恢复机制，提供了一个HttpRequestRetryHandler 接口实现

```
DefaultHttpClient httpclient = new DefaultHttpClient();
HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
    public boolean retryRequest(IOException exception, int excutionCount, HttpContext context) {
        if (executionCount >=5) {
            //超过最大重试次数，那么就不在继续了
            return false;
        }
        if (exception instanceof NoHttpResponseException) {
            // 如果服务器丢掉了连接，那么就重试
            return true;
        }
        if (exception instanceof SSLHandshakeException) {
            // 不要重试SSL握手异常
            return false;
        }
        HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
            // 如果请求被认为是幂等的，那么就重试
            return true;
        }
        return false;
    }
}
httpclient.setHttpRequestRetryHandler(myRetryHandler);

```
> 拉姆的表达式写法
```
HttpRequestRetryHandler myRetryHandler = (exception, executionCount, context) -> {
    if (executionCount >= 3) {
          /**超过最大重试次数 不要重试*/
          return false;
      }
      if (exception instanceof InterruptedIOException) {
          /***指定时间未获得结果，超时重试*/
          return true;
      }
      if (exception instanceof UnknownHostException) {
          /**未知host 不重试*/
          return false;
      }
      if (exception instanceof ConnectTimeoutException) {
          /**链接被拒绝，不重试*/
          return false;
      }
      if (exception instanceof SSLException) {
          /**ssl 权限认证异常，不重试*/
          return false;
      }
      HttpClientContext clientContext = HttpClientContext.adapt(context);
      HttpRequest request = clientContext.getRequest();
      boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
      if (idempotent) {
          /*** 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的 */
          return true;
      }
      return false;
  };
```

## 中止请求
> 在一些情况下，由于目标服务器的高负载或客户端有很多活动的请求，那么HTTP请求执行会在预期的时间框内而失败。
这时，就可能不得不过早地中止请求，解除封锁在I/O执行中的线程封锁。被HttpClient执行的HTTP请求可以在执行的任意阶段通过调用HttpUriRequest#abort()方法而中止。
这个方法是线程安全的，而且可以从任意线程中调用。
当一个HTTP请求被中止时，它的执行线程就封锁在I/O操作中了，而且保证通过抛出InterruptedIOException异常来解锁

## HTTP 协议拦截
> HTTP协议拦截器是一个实现了特定HTPP协议方面的惯例。
通常协议拦截器希望作用于一个特定头部信息上，或者一族收到报文的相关头部信息，或使用一个特定的头部或一族相关的头部信息填充发出的报文。
协议拦截器也可以操纵包含在报文中的内容实体，透明的内容压缩/解压就是一个很好的示例。通常情况下这是由包装器实体类使用了“装饰者”模式来装饰原始的实体完成的。
一些协议拦截器可以从一个逻辑单元中来结合。
协议拦截器也可以通过共享信息来共同合作-比如处理状态-通过HTTP执行上下文。协议拦截器可以使用HTTP内容来为一个或多个连续的请求存储一个处理状态。
通常拦截器执行的顺序不应该和它们基于的特定执行上下文状态有关。
如果协议拦截器有相互依存关系，那么它们必须按特定顺序来执行，正如它们希望执行的顺序一样，它们应该在相同的序列中被加到协议处理器。
协议拦截器必须实现为线程安全的。和Servlet相似，协议拦截器不应该使用实例变量，除非访问的那些变量是同步的。
这个示例给出了本地内容在连续的请求中怎么被用于持久一个处理状态的：

```
DefaultHttpClient httpclient = new DefaultHttpClient();
HttpContext localContext = new BasicHttpContext();
//AtomicInteger，一个提供原子操作的Integer的类
AtomicInteger count = newe AtomicInteger(1);
localContext.setAttribute("count", count);
httpclient.addRequestIntegerceptor(new HttpRequestInterceptor() {
    public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
        AtomicInteger count = (AtomicInteger) context.getAttribute("count");
        request.addHeader("Count", Integer.toString(count.getAndIncrement()));
    }
})
HttpGet httpget = new HttpGet("http://localhost/");
for (int i = 0; i < 10; i++) {
    HttpResponse response = httpclient.execute(httpget, localContext);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        entity.consumeContent();
    }
}
```

## HTTP参数
> HttpParams接口代表了定义组件运行时行为的一个不变的值的集合。很多情况下，HttpParams和HttpContext相似。二者之间的主要区别是它们在运行时使用的不同。这两个接口表示了对象的集合，它们被视作为访问对象值的键的Map，但是服务于不同的目的：

1. HttpParams旨在包含简单对象：整型，浮点型，字符串，集合，还有运行时不变的对象。
2. HttpParams希望被用在“一次写入-多处准备”模式下。HttpContext旨在包含很可能在HTTP报文处理这一过程中发生改变的复杂对象
3. HttpParams的目标是定义其它组件的行为。通常每一个复杂的组件都有它自己的HttpParams对象。HttpContext的目标是来表示一个HTTP处理的执行状态。通常相同的执行上下文在很多合作的对象中共享

## 参数层次
> 在HTTP请求执行过程中，HttpRequest对象的HttpParams是和用于执行请求的HttpClient实例的HttpParams联系在一起的。
这使得设置在HTTP请求级别的参数优先于设置在HTTP客户端级别的HttpParams。
推荐的做法是设置普通参数对所有的在HTTP客户端级别的HTTP请求共享，而且可以选择性重写具体在HTTP请求级别的参数

```
DefaultHttpClient httpclient = new DefaultHttpClient();
httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0));
httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
HttpGet httpget = new HttpGet("http://www.google.com/");
httpget.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
httpget.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
    public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
        System.out.println(request.getParams().getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
        System.out.println(request.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
        System.out.println(request.getParams().getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
        System.out.println(request.getParams().getParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING));
    }
});
```
> 输出结果
```
HTTP/1.1
UTF-8
false
null
```

### HTTP 参数bean
> HttpParams接口允许在处理组件的配置上很大的灵活性。很重要的是，新的参数可以被引入而不会影响老版本的二进制兼容性。
然而，和常规的Java bean相比，HttpParams也有一个缺点：HttpParams不能使用DI框架来组合。
为了缓解这个限制，HttpClient包含了一些bean类，它们可以用来按顺序使用标准的Java eban惯例初始化HttpParams对象。
```
HttpParams params = new BasicHttpParams();
HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
paramsBean.setVersion(HttpVersion.HTTP_1_1);
paramsBean.setContentCharset("UTF-8");
paramsBean.setUseExpectContinue(true);
System.out.println(params.getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
System.out.println(params.getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
System.out.println(params.getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
System.out.println(params.getParameter(CoreProtocolPNames.USER_AGENT));
```
> 输出结果
```
HTTP/1.1
UTF-8
false
null
```
## HTTP 请求执行参数
> 这些参数会影响到请求执行的过程：

1. http.protocol.version : 如果没有在请求对象中设置明确的版本信息，它就定义了使用的HTTP协议版本。这个参数期望得到一个ProtocolVersion类型的值。如果这个参数没有被设置，那么就使用HTTP/1.1
2. http.protocol.element-charset : 定义了编码HTTP协议元素的字符集。这个参数期望得到一个java.lang.String类型的值。如果没有这个参数被设置，那么就使用ISO-8859-1。
3. http.useragent：定义了头部信息User-Agent的内容。这个参数期望得到一个java.lang.String类型的值。如果这个参数没有被设置，那么HttpClient将会为它自动生成一个值。
4. 'http.protocol.strict-transfer-encoding'：定义了响应头部信息中是否含有一个非法的Transfer-Encoding，都要拒绝掉。
5. 'http.protocol.expect-continue'：为包含方法的实体激活Expect: 100-Continue握手。Expect: 100-Continue握手的目的是允许客户端使用请求体发送一个请求信息来决定源服务器是否希望在客户端发送请求体之前得到这个请求（基于请求头部信息）。Expect: 100-Continue握手的使用可以对需要目标服务器认证的包含请求的实体（比如POST和PUT）导致明显的性能改善。Expect: 100-Continue握手应该谨慎使用，因为它和HTTP服务器，不支持HTTP/1.1协议的代理使用会引起问题。这个参数期望得到一个java.lang.Boolean类型的值。如果这个参数没有被设置，那么HttpClient将会试图使用握手。
6. 'http.protocol.wait-for-continue'：定义了客户端应该等待100-Continue响应最大的毫秒级时间间隔。这个参数期望得到一个java.lang.Integer类型的值。如果这个参数没有被设置，那么HttpClient将会在恢复请求体传输之前为确认等待3秒。

# 第二章 链接管理
> HttpClient有一个对连接初始化和终止，还有在活动连接上I/O操作的完整控制。而连接操作的很多方面可以使用一些参数来控制。

> 这些参数可以影响连接操作：

1. http.socket.timeout : 定义了套接字的毫秒级超时时间（SO_TIMEOUT）,这就是等待数据，换句话说，在两个连续的数据包之间最大的闲置时间。
如果超时时间是0 ，就解释为是一个无限大的超时时间。这个参数期望得到一个java.lang.Integer类型的值，如果这个参数没有被设置，那么读取操作就不会超时（无限大的超时时间）。
2. http.tcp.nodelay : 决定了是否使用Nagle算法。Nagle算法视图通过最小化发送的分组数量来节省带宽。
当应用程序希望降低网络延迟并提高性能时，他们可以关闭Nagle算法（也就是开启TCP_NODELAY）。
数据将会更早发送，增加了带宽消耗的成本。这个参数期望得到一个java.lang.Boolean类型的值。 如果这个参数没有被设置，那么TCP_NODELAY就会开启（无延迟）。
3. http.socket.buffer-size : 决定了内部套接字缓冲使用的大小，来缓冲数据同时接收/传输HTTP b报文。
这个参数期望得到一个java.lang.Integer类型的值。如果这个cask没有被设置，那么HttpClient将会分配8192个字节的套接字缓存。
4. http.socket.linger : 使用指定的秒数拖延时间来设置SO_LINGER 。最大的链接超时时值是平台指定的。值0 暗示了这个选项是关闭的。
值-1 暗示了使用JRE默认的，这个设置仅仅影响套接字关闭操作。如果这个参数没有被设置，那么就假设值为-1（JRE默认）。
5. http.connection.timeout: 决定了直接链接建立时的毫秒级超时时间。超时时间的值为0 解释为一个无限大的时间。
这个参数期望得到一个java.lang.Integer 类型的值，如果这个参数没有被设置，链接操作将不会超时（无限大的超时时间）
6. http.connection.stalecheck : 决定了是否使用旧的链接检查。当在一个连接之上执行一个请求而服务器端的链接已经关闭时，关闭旧的链接检查可能导致在获得一个I/O错误风险时显著的性能提升（对于每一个请求，检查时间可以达到30毫秒）。
这个参数期望得到一个java.lang.Boolean类型的值。出于性能的关键操作，检查应该被关闭。如果这个参数没有被设置，那么旧的连接将会在每个请求执行之前执行。
7. http.connection.max-line-length：决定了最大请求行长度的限制。如果设置为一个正数，任何HTTP请求行超过这个限制将会引发java.io.IOException异常。
负数或零将会关闭这个检查。这个参数期望得到一个java.lang.Integer类型的值。如果这个参数没有被设置，那么就不强制进行限制了。
8. http.connection.max-header-count : 决定了允许的最大HTTP头部信息数量。
如果设置为一个正数，从数据流中获得的HTTP头部信息数量超过这个限制就会引发java.io.IOException异常。负数或零将会关闭这个检查。
这个参数期望得到一个java.lang.Integer类型的值。如果这个参数没有被设置，那么就不强制进行限制了。
9. http.connection.max-status-line-garbage：决定了在期望得到HTTP响应状态行之前可忽略请求行的最大数量。
使用HTTP/1.1持久性连接，这个问题产生的破碎的脚本将会返回一个错误的Content-Length（有比指定的字节更多的发送）。
不幸的是，在某些情况下，这个不能在错误响应后来侦测，只能在下一次之前。所以HttpClient必须以这种方式跳过那些多余的行。
这个参数期望得到一个java.lang.Integer类型的值。0是不允许在状态行之前的所有垃圾/空行。使用java.lang.Integer#MAX_VALUE来设置不限制的数字。如果这个参数没有被设置那就假设是不限制的。

## 持久链接
> 从一个主机向另外一个建立连接的过程是相当复杂的，而且包含了两个终端之间的很多包的交换，它是相当费时的。
连接握手的开销是很重要的，特别是对小量的HTTP报文。如果打开的连接可以被重用来执行多次请求，那么就可以达到很高的数据吞吐量。
HTTP/1.1强调HTTP连接默认情况可以被重用于多次请求。HTTP/1.0兼容的终端也可以使用相似的机制来明确地交流它们的偏好来保证连接处于活动状态，
也使用它来处理多个请求。HTTP代理也可以保持空闲连接处于一段时间的活动状态，防止对相同目标主机的一个连接也许对随后的请求需要。
保持连接活动的能力通常被称作持久性连接。HttpClient完全支持持久性连接

##　HTTP 链接路由
> HttpClient能够直接或通过路由建立连接到目标主机，这会涉及多个中间连接，也被称为跳。HttpClient区分路由和普通连接，通道和分层。
通道连接到目标主机的多个中间代理的使用也称作是代理链。
普通路由由连接到目标或仅第一次的代理来创建。通道路由通过代理链到目标连接到第一通道来建立。没有代理的路由不是通道的，分层路由通过已存在连接的分层协议来建立。
协议仅仅可以在到目标的通道上或在没有代理的直接连接上分层

## 路由计算
> RouteInfo接口代表关于最终涉及一个或多个中间步骤或跳的目标主机路由的信息。
HttpRoute是RouteInfo的具体实现，这是不能改变的（是不变的）。HttpTracker是可变的RouteInfo实现，由HttpClient在内部使用来跟踪到最大路由目标的剩余跳数。
HttpTracker可以在成功执行向路由目标的下一跳之后更新。HttpRouteDirector是一个帮助类，可以用来计算路由中的下一跳。这个类由HttpClient在内部使用。
HttpRoutePlanner是一个代表计算到基于执行上下文到给定目标完整路由策略的接口。
HttpClient附带两个默认的HttpRoutePlanner实现。ProxySelectorRoutePlanner是基于java.net.ProxySelector的。
默认情况下，它会从系统属性中或从运行应用程序的浏览器中选取JVM的代理设置。DefaultHttpRoutePlanner实现既不使用任何Java系统属性，也不使用系统或浏览器
的代理设置。它只基于HTTP如下面描述的参数计算路由。

## 安全 HTTP 链接
> 如果信息在两个不能由非认证的第三方进行读取或修改的终端之间传输，HTTP连接可以被认为是安全的。
SSL/TLS协议是用来保证HTTP传输安全使用最广泛的技术。而其它加密技术也可以被使用。通常来说，HTTP传输是在SSL/TLS加密连接之上分层的。

## HTTP 路由参数
> 这些参数可以影响路由计算：
1. http.route.default-proxy : 定义可以被不使用JRE设置的默认路由规则者使用的代理主机。这个参数期望得到一个HttpHost类型的值。如果这个参数没有被设置，那么就会尝试链接到目标
2. http.route.local-address: 定义一个本地地址由所有默认路由规划者来使用，有多个网络接口的机器中，这个参数可以被用于从链接源中选择网络接口。
这个参数期望得到一个java.net.InetAddress类型的值。如果这个参数没有被设置，将会自动使用本地地址。
3. http.route.forced-route：定义一个由所有默认路由规划者使用的强制路由。代替了计算路由，给定的强制路由将会被返回，尽管它指向一个完全不同的目标主机。
这个参数期望得到一个HttpRoute类型的值。如果这个参数没有被设置，那么就使用默认的规则建立连接到目标服务器。

## 套接字工厂
> HTTP连接内部使用java.net.Socket对象来处理数据在线路上的传输。它们依赖SocketFactory接口来创建，初始化和连接套接字。
这会使得HttpClient的用户可以提供在运行时指定套接字初始化代码的应用程序。PlainSocketFactory是创建和初始化普通的（不加密的）套接字的默认工厂。
创建套接字的过程和连接到主机的过程是不成对的，所以套接字在连接操作封锁时可以被关闭。
```
PlainSocketFactory sf = PlainSocketFactory.getSocketFactory();
Socket socket = sf.createSocket();
HttpParams params = new BasicHttpParams();
params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000L)；
sf.connectSocket(socket, "localhost", 8080, null, -1, params)
```

### 安全套接字分层
> 以创建在已经存在的普通套接字之上的分层套接字。套接字分层主要通过代理来创建安全的套接字。HttpClient 附带实现了SSL/TLS分层的SSLSocketFactory.
请注意在HttpClient 不使用任何自定义加密功能。它完全依赖标准的java密码学（JCE） 和安全套接字（JSEE）扩展
Layered

LayeredSocketFactory是SocketFactory接口的扩展。分层的套接字工厂可
以创建在已经存在的普通套接字之上的分层套接字。套接字分层主要通过代理来创建安全的套接字。HttpClient附带实现了SSL/TLS分层的SSLSocketFactory。
请注意HttpClient不使用任何自定义加密功能。它完全依赖于标准的Java密码学（JCE）和安全套接字（JSEE）扩展。

## SSL/TLS的定制
> HttpClient 使用SSLSocketFactory来创建SSL链接。SSLSocketFactory允许高度定制。它可以使用javax.net.ssl.SSLContext的实例作为参数，并使用它来创建定制SSL连接。

```
TrustManager easyTrustManager = new X509TrustManager() {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // 这很简单
    }
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //这很简单
    }
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
SSLContext sslcontext = SSLContext.getInstance("TLS");
sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
SSLSocket socket = (SSLSocket) sf.createSocket();
socket.setEnabledCipherSuites(new String[] { "SSL_RSA_WITH_RC4_128_MD5" });
HttpParams params = new BasicHttpParams();
params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000L);
sf.connectSocket(socket, "locahost", 443, null, -1, params);
```
> SSLSocketFactory 的定制暗示出一定程度的SSL/TLS 协议概念，，这个详细的解释超出了本文档的范围。请参考Java的安全套接字扩展[http://java.sun.com/j2se/1.5.0/docs/guide/
security/jsse/JSSERefGuide.html]，这是javax.net.ssl.SSLContext和相关工具的详细描述。

## 主机名称验证

> 除了信任验证和客户端认证在SSL/TLS协议级上进行，一旦连接建立之后，HttpClient能可选地验证目标主机名匹配存储在服务器的X.509认证中的名字。
这个认证可以提供额外的服务器信任材料的真实保证。X509主机名验证接口代表了主机名验证的策略。HttpClient附带了3个X509主机名验证器。很重要的一点是：主机名验证不应该混淆SSL信任验证

> StrictHostnameVerifier: 严格的主机验证在Sun Java 1.4, Sun Java 5 和Sun Java 6 中是相同的
而且也非常接近IE6。 这个实现似乎是兼容RFC 2818处理通配符的。主机名必须匹配第一个CN或任意的subject-alt.在CN和其它任意的subject-alt中可能会出现统配符

1. BrowserCompatHostnameVerifier：主机名验证器和Curl和Firefox的工作方式是相同的。
主机名必须匹配第一个CN或任意的subject-alt。在CN和其它任意的subject-alt中可能会出现通配符。
BrowserCompatHostnameVerifier和StrictHostnameVerifier的唯一不同是使用BrowserCompatHostnameVerifier匹配所有子域的通配符（比如”*.foo.com”），
包括”a.b.foo.com”。
2. AllowAllHostnameVerifier：这个主机名验证器基本上是关闭主机名验证的。这个实现是一个空操作，而且不会抛出javax.net.ssl.SSLException异常。
每一个默认的HttpClient使用BrowserCompatHostnameVerifier的实现。如果需要的话，它可以指定不同的主机名验证器实现。

```
SSLSocketFactory sf = new SSLSocketFactory(SSLContext.getInstance("TLS"));
sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFiER);
```

## 协议模式
> Scheme类代表了一个协议模式，比如“http”或“https”同时包含一些协议属性，比如默认端口，用来为给定协议创建java.net.Socket实例的套接字工厂。
SchemeRegistry类用来维持一组Scheme，当去通过请求URI建立连接时，HttpClient可以从中选择：

```
Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
SSLSocketFactory sf = new SSLSocketFactory(SSLContext.getInstance("TLS"));
sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
Scheme htps = new Scheme("https", sf , 443);
SchemeRegistry sr = new SchemeRegistry();
sr.register(http);
sr.register(https);
````

## 代理配置
> 尽管 HttpClient 了解复杂的路由模式和代理链，它仅支持简单直接的或开箱的跳转代理链接。
告诉HttpClient通过代理去连接到目标主机的最简单方式是通过设置默认的代理参数：

```
DefaultHttpClient httpclient = new DefaultHttpClient();
HttpHost proxy = new HttpHost("someproxy", 8080);
httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
```

> 也可以构建 HttpClient 使用标准的JRE代理选择器来获得代理信息

```
DefaultHttpClient httpclient = new DefaultHttpClient();
ProxySelectorRoutePlanner routePlanner = new ProxySelectorRoutePlanner(httpclient.getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
httpclient.setRoutePlanner(routePlanner);
```
> 另外一种选择，可以提供一个定制的RoutePlanner 实现来获得HTTP路由计算处理上的复制的控制：
```
DefaultHttpClient httpclient = new DefaultHttpClient();
httpclient.setRoutePlanner(new HttpRoutePlanner() {
    public HttpRoute determinRoute(HttpHost target, HttpRequest request, HttpContext context) throwns HttpException {
        return new HttpRoute(target, null, new HttpHost("someproxy", 8080), "https".equalsIgnorCase(target.getSchemeName()));
    }
});
```
## HTTP 链接管理器
### 链接操作器
> 连接操作是客户端的低层套接字或可以通过外部实体，通常称为连接操作的被操作的状态的连接。
OperatedClientConnection接口扩展了HttpClientConnection接口而且定义了额外的控制连接套接字的方法。ClientConnectionOperator接口代表了创建实例和更新那些对象低层套接字的策略。
实现类最有可能利用SocketFactory来创建java.net.Socket实例。ClientConnectionOperator接口可以让HttpClient的用户提供一个连接操作的定制策略和提供可选实现OperatedClientConnection接口的能力。

### 管理链接 和 管理链接器
> HTTP连接是复杂的，有状态的，线程不安全的对象需要正确的管理以便正确地执行功能。HTTP连接在同一时间仅仅只能由一个执行线程来使用。
HttpClient采用一个特殊实体来管理访问HTTP连接，这被称为HTTP连接管理器，代表了ClientConnectionManager接口。
一个HTTP连接管理器的目的是作为工厂服务于新的HTTP连接，管理持久连接和同步访问持久连接来确保同一时间仅有一个线程可以访问一个连接。
内部的HTTP连接管理器和OperatedClientConnection实例一起工作，但是它们为服务消耗器ManagedClientConnection提供实例。
ManagedClientConnection扮演连接之上管理状态控制所有I/O操作的OperatedClientConnection实例的包装器。
它也抽象套接字操作，提供打开和更新去创建路由套接字便利的方法。ManagedClientConnection实例了解产生它们到连接管理器的链接，而且基于这个事实，当不再被使用时，它们必须返回到管理器。
ManagedClientConnection类也实现了ConnectionReleaseTrigger接口，可以被用来触发释放连接返回给管理器。
一旦释放连接操作被触发了，被包装的连接从ManagedClientConnection包装器中脱离，OperatedClientConnection实例被返回给管理器。
尽管服务消耗器仍然持有ManagedClientConnection实例的引用，它也不再去执行任何I/O操作或有意无意地改变的OperatedClientConnection状态。
这里有一个从连接管理器中获取连接的示例：

```
HttpParams params = new BasicHttpParams();
Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(),80);
SchemeRegistry sr = new SchemeRegistry();
sr.regiestry(http);
ClientConnectionManager connMrg = new SingleClientConnManager(params, sr);
// 请求新链接，这可能是一个很长的过程。
ClientConnectionRequest conRequest = connMrg.requestConnection(new HttpRoute(new HttpHost("localhost", 80)), null);
// 等待链接10 秒
ManagedClientConnection conn = connRequest.getConnection(10.TimeUnit.SECONNDS);
try {
    // 用连接在做有用的事情。当完成时释放连接。
    conn.releaseConnection();
} cathc (IOException ex) {
    // 在I/O error之上终止连接。
    conn.abortConnection();
    throw ex;
}
```

> 如果需要，连接请求可以通过调用来ClientConnectionRequest#abortRequest()方法过早地中断。这会解锁在ClientConnectionRequest#getConnection()方法中被阻止的线程。
一旦响应内容被完全消耗后，BasicManagedEntity包装器类可以用来保证自动释放低层的连接。HttpClient内部使用这个机制来实现透明地对所有从HttpClient#execute()方法中获得响应释放连接：

```
ClientConnectionRequest connRequest = connMrg.requestConnection(new HttpRoute(new HttpHost("localhost", 80)), null);
ManagedClientConnection conn = connRequest.getConnection(10, TimeUnit.SECONDS);
try {
    BasicHttpRequest request = new BasicHttpRequest("GET", "/");
    conn.sendRequestHeader(request);
    HttpResponse response = conn.receiveResponseHeader();
    conn.receiveResponseEntity(response);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        BasicManagedEntity managedEntity = new BasicManagedEntity(entity, conn, true);
        // 替换实体
        response.setEntity(managedEntity);
    }
    // 使用响应对象做有用的事情。当响应内容被消耗后这个连接将会自动释放。
} catch (IOException ex) {
    //在I/O error之上终止连接。
    conn.abortConnection();
    throw ex;
}
```
#### 优雅拼接url参数方式 get方式
```
List<BasicNameValuePair> nvps = new ArrayList<>();
nvps.add(new BasicNameValuePair("q", queryRequest.getKeyword()));
queryRequest.setUrl(YOUDAO_URL + URLEncodedUtils.format(nvps, Consts.UTF_8));

这种就很不好看，还容易出错
String url = MicrosoftApiUtil.SERVICE_DETECT_URL + "appId=" + URLEncoder.encode(MicrosoftApiUtil.token, MicrosoftApiUtil.ENCODING) + MicrosoftApiUtil.PARAM_TEXT_SINGLE + URLEncoder.encode(text, MicrosoftApiUtil.ENCODING);
```


















