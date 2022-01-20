package com.demo.thread;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lz
 * @date 2021-11-18 13:20
 */
public class ThreadDemo {
    private  int n = 0;

    /**
     * 锁对象，调用同一个对象时 会阻塞
     * @param threadName
     */
    public synchronized void func(String threadName){
        for (int i = 0; i < 10; i++) {
            System.out.println("threadName: "+threadName+";n: "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 锁整个class文件
     * @param threadName
     */
    public static synchronized void funcStatic(String threadName){
        for (int i = 0; i < 10; i++) {
            System.out.println("threadName: "+threadName+";n: "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this代表当前对象，效果同锁对象
     * @param threadName
     */
    public void funcThis(String threadName){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                System.out.println("threadName: "+threadName+";n: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当调用object为同一对象时，线程阻塞
     * @param threadName
     * @param object
     */
    public void funcObject(String threadName,Object object){
        synchronized (object){
            for (int i = 0; i < 10; i++) {
                System.out.println("threadName: "+threadName+";n: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 锁定class文件
     * @param threadName
     */
    public void funcClass(String threadName){
        synchronized (ThreadDemo.class){
            for (int i = 0; i < 10; i++) {
                System.out.println("threadName: "+threadName+";n: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Object object1 = new Object();
        Long startTime = System.currentTimeMillis();
        ThreadDemo threadDemo = new ThreadDemo();
        ThreadDemo threadDemo1 = new ThreadDemo();
        Thread t1 = new Thread(
                () -> {
//                        threadDemo.func("thread-1");
//                    ThreadDemo.funcStatic("thread-1");
//                    threadDemo.funcThis("thread-1");
//                    threadDemo.funcObject("thread-1",object);
                    threadDemo.funcClass("thread-1");
                }
        );
        Thread t2 = new Thread(
                () -> {
//                        threadDemo.func("thread-2");
//                    ThreadDemo.funcStatic("thread-2");
//                    threadDemo.funcThis("thread-2");
//                    threadDemo.funcObject("thread-2",object);
                    threadDemo.funcClass("thread-2");

                }
        );
        Thread t3 = new Thread(
                () -> {
//                    threadDemo1.func("thread-3");
//                    ThreadDemo.funcStatic("thread-3");
//                    threadDemo1.funcThis("thread-3");
//                    threadDemo.funcObject("thread-3",object1);
                    threadDemo1.funcClass("thread-3");
                }
        );
        t1.start();
        t2.start();
        t3.start();
        //join的效果是等待线程完成后执行
        t1.join();
        t2.join();
        t3.join();
        Long endTime = System.currentTimeMillis();
        System.out.println("do it over: "+threadDemo.n+";use time: "+(endTime-startTime));


        AtomicLong atomicLong = new AtomicLong();
        atomicLong.incrementAndGet();
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
    }
}
