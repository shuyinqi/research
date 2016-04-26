package com.shuyinqi.base;

/**
 * Created by jiayusun on 2016/4/26.
 */
public class BaseMain {
    public static void main(String[] args) {
        //接口的new
        TestInterface ti = new TestInterface() {};
        //抽象类的new
        TestAbstract tab = new TestAbstract() {};
        System.out.println(ti.getClass().getName());
    }
}
