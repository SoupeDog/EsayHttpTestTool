package org.xavier.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xavier.domain.exception.PropertiesException;

import java.util.Hashtable;

/**
 * 描述信息：<br/>
 * 测试 TestCase
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/7/25
 * @since Jdk 1.8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCaseTest {
    static{
        Hashtable hashtable=new Hashtable();
        hashtable.put("aa","初始");
        DataCenter.initData(hashtable);
    }

    @Test
    public void test() throws PropertiesException {
        TestCase testCase = new TestCase();
        testCase.startTest();
    }

    @Test
    public void test2() throws PropertiesException {
        TestCase2 testCase = new TestCase2();
        testCase.startTest();
    }
}