package org.xavier.factory;


import org.xavier.domain.DataCenter;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2017.03.30.
 * @since Jdk 1.8
 */
public class DataCenterFactory {
    private static DataCenter dataCenter = new DataCenter();

    /**
     * 工厂构造器私有化
     */
    private DataCenterFactory() {
    }

    /**
     * @return 单例的HttpEmitter对象
     */
    public static DataCenter getInstance() {
        return dataCenter;
    }
}
