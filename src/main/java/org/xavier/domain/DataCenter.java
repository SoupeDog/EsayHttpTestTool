package org.xavier.domain;

import java.util.Hashtable;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * 描述信息：<br/>
 * 测试用例数据缓存中心，用于缓存 例如："customerVar":#{customerVarValue} 标识的Key-Val值
 *
 * @author Xavier
 * @version 1.0
 * @date 2017.03.13.
 * @since Jdk 1.8
 */
public class DataCenter {
    private static Hashtable<String, Object> hashtable;

    static {
        hashtable = new Hashtable<String, Object>();
        System.out.println("数据中心初始化");
    }

    public DataCenter() {
    }

    /**
     * 从缓存中心获取Key对应的Val值
     *
     * @param key Key
     * @return Key对应的Val值，可能为空
     */
    public static Object getData(String key) {
        return hashtable.get(key) == null ? null : hashtable.get(key);
    }

    /**
     * 向缓存中心存储一堆Key-Val信息
     *
     * @param key Key
     * @param val Key对应的Val
     * @return 存储是否成功。True：成功
     */
    public static Boolean setData(String key, Object val) {
        if (hashtable.containsKey(key)) {
            System.out.println(ansi().eraseScreen().render("@|red " + key + "(" + hashtable.get(key) + ")\t被覆盖为：\t" + key + "(" + val + ")" + "|@"));
            hashtable.put(key, val);
            return true;
        }
        int size = hashtable.size();
        hashtable.put(key, val);
        return hashtable.size() > size;
    }

    public static void initData(Map<String, Object> map) {
        hashtable.putAll(map);
    }
}
