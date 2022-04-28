package com.demo.hutool;

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


        /**
         * 当代码中使用第一种方式创建字符串对象时，JVM 首先会检查该对象是否在字符串常量池
         * 中，如果在，就返回该对象引用，否则新的字符串将在常量池中被创建。这种方式可以减少
         * 同一个值的字符串对象的重复创建，节约内存。
         * String str = new String(“abc”) 这种方式，首先在编译类文件时，"abc"常量字符串将
         * 会放入到常量结构中，在类加载时，“abc"将会在常量池中创建；其次，在调用 new 时，
         * JVM 命令将会调用 String 的构造函数，同时引用常量池中的"abc” 字符串，在堆内存中
         * 创建一个 String 对象；最后，str 将引用 String 对象。
         */
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

        String i = e.intern();
        System.out.println(i == f);//true
        System.out.println(i == e);//true
        System.out.println(i == g);//false
    }
}
