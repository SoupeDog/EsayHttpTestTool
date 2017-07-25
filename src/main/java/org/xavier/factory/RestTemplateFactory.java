package org.xavier.factory;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息：<br/>
 * RestTemplate工厂
 *
 * @author Xavier
 * @version 1.0
 * @date 2017.03.11.
 * @since Jdk 1.8
 */
public class RestTemplateFactory {

    private static RestTemplate restTemplate;

    static {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20); //应该从配置拿到

        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setBufferSize(4128)
                .build();
        org.apache.http.client.HttpClient client = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultConnectionConfig(connectionConfig).build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
        List list=new ArrayList();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        list.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(list);
        restTemplate.setErrorHandler(new ResponseErrorHandler() { //设置一个不起任何作用的error handler
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        });
    }

    /**
     * 工厂构造器私有化
     */
    private RestTemplateFactory() {
    }

    /**
     * @return 单例的RestTemplate对象
     */
    public static RestTemplate getInstance() {
        return restTemplate;
    }

}
