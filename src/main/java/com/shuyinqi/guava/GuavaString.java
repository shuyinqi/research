package com.shuyinqi.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiayusun on 2016/4/25.
 */
public class GuavaString {
    //isNullOrEmpty(input)方法判断字符串是否为空
    public static void testIsNullOrEmpty(){
        String input= "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty?"is":"is not") + " null or empty.");
    }
    //获得两个字符串相同的前缀或者后缀
    public static void testCommonPrefixOrSuffix(){
        //Strings.commonPrefix(a,b) demo
        String a= "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }
    //Strings的padStart和padEnd方法来补全字符串
    public static void testPadStartOrPadEnd(){
        int minLength=4;
        String padEndResult = Strings.padEnd("123",minLength,'0');
        System.out.println("padEndResult is "+padEndResult);

        String padStartResult = Strings.padStart("1",2,'0');
        System.out.println("padStartResult is "+padStartResult);
    }
    //使用Splitter类来拆分字符串
    public static void testSplitter(){
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}").trimResults().omitEmptyStrings().split("hello,word,,世界，水平");
        for(String item : splitResults){
            System.out.println(item);
        }

        String toSplitString = "a=b;c=d,e=f";
        Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String,String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }

    }
    //Joiner类来做字符串的合并
    public  static void testJoiner(){
        String joinResult = Joiner.on(" ").join(new String[]{"hello","world"});
        System.out.println(joinResult);

        Map<String,String> map = new HashMap<>();
        map.put("a","b");
        map.put("c","d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }



    public static void main(String[] args) {
//        testPadStartOrPadEnd();
//        testSplitter();
        testJoiner();
    }
}
