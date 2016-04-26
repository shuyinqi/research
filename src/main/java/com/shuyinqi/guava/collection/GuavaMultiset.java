package com.shuyinqi.guava.collection;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by jiayusun on 2016/4/25.
 * Multiset看似是一个Set，但是实质上它不是一个Set，它没有继承Set接口，它继承的是Collection<E>接口，你可以向Multiset中添加重复的元素，Multiset会对添加的元素做一个计数
 */
public class GuavaMultiset {

    public static void main(String[] args) {
        Multiset multiset = HashMultiset.create();
        String sentences="this is a story, there is a good girl in the story.";
        Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            multiset.add(word);
        }
        for (Object element : multiset.elementSet()) {
            System.out.println((String)element + ":" + multiset.count(element));
        }
        /***
         * HashMultiset: 元素存放于 HashMap
           LinkedHashMultiset: 元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定
           TreeMultiset:元素被排序存放于TreeMap
           EnumMultiset: 元素必须是 enum 类型
           ImmutableMultiset: 不可修改的 Mutiset
         */
    }
}
