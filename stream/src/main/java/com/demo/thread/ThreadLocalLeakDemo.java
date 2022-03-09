package com.demo.thread;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 11:03
 * Description: No Description
 */
public class ThreadLocalLeakDemo {
    public static void main(String[] args) {
        //这种存在强引用 不会被回收
        String str = new String("Test ThreadLocal memory lead!");
        WeakReference<String> weakReference = new WeakReference<String>(str);

        //这里没有强引用，会被回收
        WeakReference<String> weakReference1 =
                new WeakReference<String>(new String("Test ThreadLocal1 memory lead!"));

        System.gc();
        if (weakReference.get() == null){
            System.out.println("weakReference已经被GC回收");
        }
        else {
            System.out.println(weakReference.get());
        }
        if (weakReference1.get() == null){
            System.out.println("weakReference1已经被GC回收");
        }
        else {
            System.out.println(weakReference1.get());
        }
    }
}
