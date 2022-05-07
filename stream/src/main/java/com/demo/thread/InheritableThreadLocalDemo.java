package com.demo.thread;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 16:01
 * Description: 子线程如何获取父线程ThreadLocal的值
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        Thread parentThread = new Thread(()->{
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            threadLocal.set(1);
            InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<>();
            integerInheritableThreadLocal.set(2);

            new Thread(()->{
                System.out.println(threadLocal.get());//null
                System.out.println(integerInheritableThreadLocal.get());//2
                /**
                 * 参照源码 new Thread -> init ()
                 */
            }).start();
        },"父线程");
        parentThread.start();
    }
}
