package com.demo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/19
 * Time: 14:57
 * Description: map putAll 属于浅拷贝
 */
public class Demo6 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("name","lzy");
        Map<String,String> map1 = new HashMap<>();
        map1.putAll(map);
        System.out.println(map);
        System.out.println(map1);
        map.remove("name");
        System.out.println(map);
        System.out.println(map1);
    }

    public static void main2(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","lzy");
        HashMap<String,String> map1 = new HashMap<>();
        map1.putAll(map);
        System.out.println(map);
        System.out.println(map1);
        map.remove("name");
        System.out.println(map);
        System.out.println(map1);
    }

    public static void main1(String[] args) {
       A a = new A("lzy");
        Map<String,A> map = new HashMap<>();
        map.put("name",a);
        Map<String,A> map1 = new HashMap<>();
        map1.putAll(map);
        System.out.println("map:");
        System.out.println(map);
        System.out.println(map1);
        System.out.println("-----------");
        map.get("name").setName("zly");
        System.out.println(map);
        System.out.println(map1);

        System.out.println("hashMap:");
        A b = new A("LZY");
        HashMap<String,A> map2 = new HashMap<>();
        map2.put("name",b);
        HashMap<String,A> map3 = new HashMap<>();
        map3.putAll(map2);
        System.out.println(map2);
        System.out.println(map3);
        System.out.println("-----------");
        map2.get("name").setName("ZLY");
        System.out.println(map2);
        System.out.println(map3);

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class A{
        private String name;
    }
}
