package org.xavier.exception;

/**
 * 描述信息：<br/>
 * 测试工具异常
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/7/25
 * @since Jdk 1.8
 */
public class TestToolException extends Exception {
    public TestToolException() {
    }

    public TestToolException(String msg) {
        super(msg);
    }

    public TestToolException(String message, Throwable cause) {
        super(message, cause);
    }
}
