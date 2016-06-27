package com.shuyinqi;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author libaoqiang
 * @Date 2016年4月14日 下午5:55:59
 *
 */
public class BaseTest {

    protected static ApplicationContext context = null;
    protected static String SUCCESS = "SUCCESS";
    protected static long id = 1;

    @BeforeClass
    public static void before() {
        context = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});

    }
}
