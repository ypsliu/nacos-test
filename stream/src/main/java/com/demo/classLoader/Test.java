package com.demo.classLoader;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import sun.swing.plaf.synth.DefaultSynthStyle;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 13:29
 * Description: No Description
 */
public class Test {
    static {
        i = 0;
//        System.out.println(i); //编译器会报错 Illegal forward reference(不合法的向前引用)
    }
    static  int i=1;

    public static void main(String[] args) {
        /**
         * 类初始化阶段按照顺序执行，首先执行 static 块中的 i=0,
         * 接着执行 static赋值操作i=1, 最后在 main 方法中获取 i 的值为1
         */
        System.out.println(i);  //输出结果为1



        /**
         * 虚拟机会保证在子类<init>()方法执行之前，父类的<clinit>()方法方法已经执行完毕
         */
//        System.out.println(C.value);// System.out.println(new C()); 两者可以对比下

        /**
         * 通过数组定义来引用类，不会触发此类的初始化(我的理解是数组的父类是Object)
         */
//        C[] cs = new C[2];

        /**
         * 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
         */
//        C.said();
        System.out.println(B.value);

    }
}
