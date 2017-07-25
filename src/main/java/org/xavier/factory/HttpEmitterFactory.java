package org.xavier.factory;


import org.xavier.emitter.HttpEmitter;

/**
 * 描述信息：<br/>
 * HttpEmitter工厂
 *
 * @author Xavier
 * @version 1.0
 * @date 2017.03.11.
 * @since Jdk 1.8
 */
public class HttpEmitterFactory {


    private static HttpEmitter httpEmitter = new HttpEmitter();

    /**
     * 工厂构造器私有化
     */
    private HttpEmitterFactory() {
    }

    /**
     * @return 单例的HttpEmitter对象
     */
    public static HttpEmitter getInstance() {
        return httpEmitter;
    }
}
