package org.xavier.domain;

import org.springframework.http.*;
import org.xavier.domain.exception.PropertiesException;
import org.xavier.emitter.HttpEmitter;
import org.xavier.factory.DataCenterFactory;
import org.xavier.factory.HttpEmitterFactory;
import org.xavier.utils.JsonHelpper;


import static org.fusesource.jansi.Ansi.ansi;

/**
 * 描述信息：<br/>
 * 测试用例基类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/7/25
 * @since Jdk 1.8
 */
public abstract class BaseTestCase {
    protected static final Long DEFAULTLIMITTIME = 100L;
    protected HttpHeaders headers;
    protected HttpEntity httpEntity;
    protected ResponseEntity responseEntity;
    protected Long startTs = 0L;
    protected Long endTs = 0L;
    protected Long timeLimit;// 红色显示临界值


    protected String description; // 测试用例描述
    protected Integer index; // 测试用例编号
    protected String methodName; // 测试用例测试方法ming
    protected String url; // 请求地址
    protected HttpMethod httpMethod;// 请求类型
    protected MediaType mediaType; // 请求类型
    protected String body; // 请求 body 体

    /**
     * 获取的单例 HttpEmitter 对象
     */
    protected static HttpEmitter httpEmitter;

    static {
        httpEmitter = HttpEmitterFactory.getInstance();
        System.out.println("测试用例基类初始化");
    }

    public BaseTestCase() {
    }

    /**
     * 开始测试
     */
    protected void startTest() throws PropertiesException {
        inItTestCase();
        send();
        printInfo();
        customerAssert();
        customerSave();
    }

    /**
     * 初始化测试数据
     */
    protected abstract void inItTestCase();

    /**
     * 发送请求
     */
    private void send() {
        startTs = System.currentTimeMillis();
        responseEntity = httpEmitter.send(url, httpMethod, httpEntity);
        endTs = System.currentTimeMillis();
    }

    /**
     * 打印请求信息
     */
    private void printInfo() throws PropertiesException {
        Long costTime = endTs - startTs;
        if (timeLimit == null) {
            timeLimit = DEFAULTLIMITTIME;
        }
        System.out.println("----------------------------------------------------------------测试用例" + "\t" + index + "\t" + "信息----------------------------------------------------------------");
        System.out.println("\t测试编号:\t" + index);
        System.out.println("\t用例描述:\t" + (description == null ? "该测试用例无描述" : description));
        System.out.println("\t请求URL:\t" + url);
        System.out.println("\t请求类型:\t" + httpMethod.name());
        System.out.println("\t响应Status:\t" + responseEntity.getStatusCode());
        if (costTime > this.timeLimit) {
            System.out.println(ansi().eraseScreen().render("@|red " + "\t耗时:\t\t" + costTime + "/毫秒" + "|@"));
        } else {
            System.out.println("\t耗时:\t\t" + costTime + "/毫秒");
        }
        System.out.println("************************************  Headers ************************************");
        System.out.println(JsonHelpper.formate(httpEntity.getHeaders()));
        System.out.println("************************************   Body   ************************************");
        System.out.println(JsonHelpper.formate(httpEntity.getBody()));
        System.out.println("************************************ Response ************************************");
        System.out.println(JsonHelpper.formate(responseEntity.getBody()));
    }

    /**
     * 自定义校验
     */
    protected abstract void customerAssert();

    /**
     * 自定缓存数据
     */
    protected abstract void customerSave();

    /**
     * 生成预期错误提示字符串<br/>
     * type 为true 应为targetVal<br/>
     * type 为false 不应为targetVal<br/>
     *
     * @return 错误信息字符串
     */
    public String printError(String varName, String targetVal, String actualVal, Boolean type) {
        if (type) {
            return "\r\n" + "\"" + varName + "\"" + "\t预期值：\t" + targetVal + "\r\n" + "\"" + varName + "\"" + "\t实际值：\t" + actualVal;// 应为
        } else {
            return "\r\n" + "\"" + varName + "\"" + "\t不应为：\t" + targetVal + "\r\n" + "\"" + varName + "\"" + "\t实际值：\t" + actualVal;// 不应为
        }
    }


    public String getDataString(String key) {
        String result;
        if (DataCenterFactory.getInstance().getData(key) != null) {
            result = DataCenterFactory.getInstance().getData(key).toString();
        } else {
            result = null;
        }
        return result;
    }

    public Object getDataObject(String key) {
        String result;
        if (DataCenterFactory.getInstance().getData(key) != null) {
            result = DataCenterFactory.getInstance().getData(key).toString();
        } else {
            result = null;
        }
        return result;
    }

    public Boolean setData(String key, Object val) {
        return DataCenterFactory.getInstance().setData(key, val);
    }
}
