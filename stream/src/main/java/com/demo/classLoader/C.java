package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 14:46
 * Description: No Description
 */
public class C extends B{
    static {
        System.out.println("C.class load");
    }
    static  int a;
    public C(){
        System.out.println("Object C create");
    }
    public static void said(){
        System.out.println("hello C");
    }
}
