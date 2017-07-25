package org.xavier.domain;

import org.springframework.http.*;

/**
 * 描述信息：<br/>
 * 测试用例实体
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/7/25
 * @since Jdk 1.8
 */
public class TestCase2 extends BaseTestCase {

    public TestCase2() {
    }

    @Override
    protected void inItTestCase() {
        // init description
        description = "这是个测试样例的示例";

        // init index
        index = 1;

        // init methodName
        methodName = "get_success";

        // init url
        url = "http://www.weather.com.cn/data/cityinfo/101010100.html";

        // init httpMethod
        httpMethod = HttpMethod.GET;
        // init mediaType
        mediaType = MediaType.APPLICATION_JSON_UTF8;

        // init Header
        headers = new HttpHeaders();
        headers.add("appId", "test");
        headers.add("token", "qw5qw78asd15eqe4q4qwe9sad");

        // init body
        body = "{\"name\":\"张三\"}";
        httpEntity = new HttpEntity(body, headers);

        // init timeLimit
        timeLimit = 100L;
    }


    @Override
    protected void customerAssert() {

    }

    @Override
    protected void customerSave() {
        setData("aa", "第二个");
    }
}
