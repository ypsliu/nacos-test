package com.demo;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/29
 * Time: 9:36
 * Description: int integer long = equals示例
 */
public class EqualsDemo {
    public static void main(String[] args) {
        int i1 = 1;
        int i2 = 1;
        System.out.println(i1 == i2);//true
        int i3 = 128;
        int i4 = 128;
        System.out.println(i3 == i4);//true
        Integer i5 = 1;
        Integer i6 = 1;
        System.out.println(i5 == i6);//true
        Integer i7 = 128;
        Integer i8 = 128;
        System.out.println(i7 == i8);//false 因为Integer有一个常量池，-128~127直接的Integer数据直接缓存进入常量池。所以1在常量池，而128不在
        System.out.println(i7.equals(i8)); //true


        String e = "abc";
        String f = "abc";
        String g = new String("abc");
        String h = new String("abc");
        System.out.println(e == f);//true
        System.out.println(e == g);//false
        System.out.println(g == h);//false
        System.out.println(g.equals(h));//true

        Integer i9 = 1;
        Long l1 = 1L;
        System.out.println(Objects.equals(i9,l1));//false
        //我们在使用Objects.equals方法，判断两个值是否相等时，一定要保证两个入参的类型要一致。
        // 否则即使两个值相同，但其结果仍然会返回false，这是一个大坑
    }
}
