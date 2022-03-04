package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 13:29
 * Description: No Description
 */
public class A {
    static {
        System.out.println("A.class load");
    }
    public A(){
        System.out.println("Object A create");
    }
}
