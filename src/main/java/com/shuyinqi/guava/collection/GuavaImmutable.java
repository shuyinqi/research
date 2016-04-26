package com.shuyinqi.guava.collection;

import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

/**
 * Created by jiayusun on 2016/4/25.
 * 创建对象的不可变拷贝是一项很好的防御性编程技巧
 */
public class GuavaImmutable {
    public static void main(String[] args) {
        //第一种方法使用builder创建
        Set<String> immutableNamedColors = ImmutableSet.<String>builder().add("red", "green","black","white","grey").build();
        //不可变集合 下一行会抛 UnsupportedOperationException
        immutableNamedColors.add("abc");
         for(String color:immutableNamedColors){
             System.out.println(color);
         }

        //第二种方法使用of静态方法创建：
        ImmutableSet<String> second =  ImmutableSet.of("red","green","black","white","grey");
        //第三种方法使用copyOf静态方法创建
        ImmutableSet.copyOf(new String[]{"red","green","black","white","grey"});
        //asList方法是在ImmutableCollection中定义，而所有的不可变集合都会从ImmutableCollection继承，所以所有的不可变集合都会有asList()方法返回当前不可变集合的list视图，这个视图也是不可变的。
        List<String> list =  second.asList();
    }
}
