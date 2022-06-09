package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 14:45
 * Description: No Description
 */
public class B extends A{
    static {
        System.out.println("B.class load");
    }
//    public static  int value = 123;
    public B(){
        System.out.println("Object B create");
    }
}
