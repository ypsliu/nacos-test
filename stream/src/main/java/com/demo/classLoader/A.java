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

    /**
     * 如果 B.value 不会触发B 的类加载过程
     * 对于静态字段， 只有直接定义这个字段的类才会被初始化
     * 因此通过其子类来引用父类中定义的静态字段，只会触发 父类的初始化而不会触发子类的初始化
     */
    public static  int value = 123;
    public A(){
        System.out.println("Object A create");
    }
}
