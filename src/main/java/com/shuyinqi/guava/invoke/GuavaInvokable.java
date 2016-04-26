package com.shuyinqi.guava.invoke;

import com.google.common.reflect.Invokable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

/**
 * Created by jiayusun on 2016/4/26.
 * Guava的Invokable是对java.lang.reflect.Method和java.lang.reflect.Constructor的流式包装。它简化了常见的反射代码的使用
 */
public class GuavaInvokable {

    public static void test(){
        System.out.println("ttttttttttttttttttt");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        GuavaInvokable guavaInvokable = new GuavaInvokable();
        Method  method = guavaInvokable.getClass().getMethod("test");
        System.out.println(Modifier.isPublic(method.getModifiers()));



    }
}
