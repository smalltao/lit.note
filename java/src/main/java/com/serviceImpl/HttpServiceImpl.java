package com.serviceImpl;

import com.Utils.StringUtils;
import com.service.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      http 接口服务实现类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/28 15:40
 */
@Service("httpService")
public class HttpServiceImpl implements HttpService {

    private static final Logger logger = Logger.getLogger(HttpServiceImpl.class);
    /**
     * 链接池管理器轮询参数
     */
    private boolean isShutdown = false;

    private PoolingHttpClientConnectionManager manager = null;
    private CloseableHttpClient httpClient = null;

    /**
     * 整个链接池最大链接数
     */
    private int maxTotal = 400;
    /**
     * 设置每个路由的最大链接数
     */
    private int maxPerRoute = 30;
    private int roundTime = 3000; // 连接池轮训时间
    private int idleTime = 30; // 链接闲置时间

    @PostConstruct
    public void doInit() {
        logger.info("Init fetch service ...");

        manager = new PoolingHttpClientConnectionManager();

        manager.setMaxTotal(maxTotal);
        manager.setDefaultMaxPerRoute(maxPerRoute);

        /**
         * 每个url分配的连接数 及目标主机对应的连接数
         * */
        Map<String, Integer> routeMap = StringUtils.getMap("vi.www.sogou.com:100|www.sogou.com:100");
        routeMap.forEach((key, value) -> {
            if (value != null && value > 0) {
                manager.setMaxPerRoute(new HttpRoute(HttpHost.create(key)), value);
            }
        });

        /**
         * 自定义重试
         * */
        HttpRequestRetryHandler RetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= 3) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                return true;
            }
            return false;
        };

        /*** 请求配置对象 */
        RequestConfig requestConfig = RequestConfig
                .custom()
                /**  从连接池中获取连接的超时时间，超过该时间未拿到可用连接 抛出ConnectionPoolTimeoutException */
                .setConnectionRequestTimeout(1000)
                /** SocketTimeout 服务器返回数据(response)的时间，超过该时间抛出read timeout */
                .setSocketTimeout(3000)
                /** 连接上服务器(握手成功)的时间，超出该时间抛出connect timeout */
                .setConnectTimeout(1000)
                .build();

        httpClient = HttpClients.custom().setConnectionManager(manager)
                .setRetryHandler(RetryHandler)
                .setDefaultRequestConfig(requestConfig)
                .build();

        /**
         * 轮询线程
         * 每3000 毫秒 关闭一次不在活动的链接以释放链接池中链接
         * 每3000 毫秒 关闭一次30秒内不在活动的链接以释放链接池中链接
         * */
        Thread evict = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isShutdown) {
                    synchronized (this) {
                        try {
                            wait(roundTime);
                            // 关闭失效的连接
                            manager.closeExpiredConnections();
                            // 可选的, 关闭30秒内不活动的连接
                            manager.closeIdleConnections(idleTime, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        /**设置为守护进程*/
        evict.setDaemon(true);
        evict.start();

        logger.info("Init Fetch service end ...");

    }

    @PreDestroy
    public void doDestroy() {
        logger.info("Destroy fetch service.");
        isShutdown = true;
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            logger.error("Destroy fetch service error.", e);
        }
    }
}
