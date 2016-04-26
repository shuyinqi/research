package com.shuyinqi.guava.collection;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jiayusun on 2016/4/25.
 * Iterators是Guava中对Iterator迭代器操作的帮助类，这个类提供了很多有用的方法来简化Iterator的操作
 */
public class GuavaIterators {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("Apple","Pear","Peach","Banana");
        Predicate<String> condition = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return ((String)input).startsWith("P");
            }
        };
        //判断迭代器中的元素是否都满足某个条件 all 方法
        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
        System.out.println("all result == " + allIsStartsWithP);
        //get方法获得迭代器中的第x个元素
        String secondElement = Iterators.get(list.iterator(), 1);
        //filter方法过滤符合条件的项
        Iterator<String> startPElements = Iterators.filter(list.iterator(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("P");
            }
        });
        //find方法返回符合条件的第一个元素
        String length5Element = Iterators.find(list.iterator(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.length() == 5;
            }
        });
        //transform方法，对迭代器元素做转换
        //我们将字符串转换成了其长度，transform方法输出的是另外一个Iterator.
        Iterator<Integer> countIterator = Iterators.transform(list.iterator(), new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });


    }
}
