package com.shuyinqi.guava.invoke;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jiayusun on 2016/4/26.
 * 原理上Google Guava的动态代理也是使用JDK的动态代理，这是做了封装，更加简便。另外一点是能够很好的检查需要代理的对象必须拥有接口
 * 不太建议使用guava的代理，只是简单的封装，但是使程序员屏蔽了代理的真实实现，性价比不高。
 */
public class GuavaReflection {

    public static void main(String[] args) {
        InvocationHandler invocationHandler = new MyInvocationHandler();

        // Guava Dynamic Proxy implement
        IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
        foo.doSomething();

        //jdk Dynamic proxy implement
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(
                IFoo.class.getClassLoader(),
                new Class<?>[]{IFoo.class},
                invocationHandler);
        jdkFoo.doSomething();
    }


    public static class MyInvocationHandler implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("proxy println something");
            return null;
        }
    }

    public static interface IFoo {
        void doSomething();
    }
}
