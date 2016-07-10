package com.shuyinqi.base;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
public class Genericity {

    private Map<String, Integer> getMap() {
        Map map = new HashMap();
        map.put("1", 11);
        map.put("2", "22");
        Map map1 = (Map<String, Genericity>) map; //能正常执行
        return map1;
    }

    @Test
    public void testMapCast1() {
        Integer a = 22;
        Map map = getMap();
        System.out.println(map.get("2"));
        System.out.println(a.equals(map.get("2")));
        System.out.println(map.get("2").equals(a));
    }

    @Test
    public void testMapCast2() {
        Integer a = 22;
        System.out.println(getMap().get("2"));
        System.out.println(a.equals(getMap().get("2")));
        System.out.println(getMap().get("2").equals(a));
    }

//
//    public static void main(String[] args) {
//        String yrbRateMapStr="{3:0,6:1,12:0.5}";
////        Map<String,String> yrb = (Map<String,String>) JSON.parse(yrbRateMapStr);
////        System.out.println(yrb);
////        Object o = yrb.get("12");
////        System.out.println(o);
////        System.out.println(yrb.get("12"));
//
//
//        Map<String, Genericity> map = (Map<String, Genericity>) JSON.parse(yrbRateMapStr);
//        System.out.println(map.getClass());
//
//        System.out.println(map.get("6").getClass());
//        System.out.println(map.get("6"));
//    }


}
