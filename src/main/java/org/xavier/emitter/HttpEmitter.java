package org.xavier.emitter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.xavier.factory.RestTemplateFactory;

/**
 * 描述信息：<br/>
 * 基于 RestTemplate 的 http 请求发射器
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/7/25
 * @since Jdk 1.8
 */
public class HttpEmitter {

    /**
     * 获取的单例RestTemplate对象
     */
    private static RestTemplate restTemplate = RestTemplateFactory.getInstance();

    public ResponseEntity send(String url, HttpMethod httpMethod, HttpEntity entity) {
        ResponseEntity responseEntity = restTemplate.exchange(url, httpMethod, entity, String.class);
        return responseEntity;
    }

}
