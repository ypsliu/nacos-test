package com.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 10:48
 * Description: No Description
 *
 *
 * 使用ThreadLocal时会发生内存泄漏的前提条件：
 * ①ThreadLocal引用被设置为null，且后面没有set，get,remove操作。
 * ②线程一直运行，不停止。（线程池）
 * ③触发了垃圾回收。（Minor GC或Full GC）
 * 使用ThreadLocal时遵守以下两个小原则:
 * ①ThreadLocal申明为private static final。
 *    Private与final 尽可能不让他人修改变更引用，
 *    Static 表示为类属性，只有在程序结束才会被回收。
 * ②ThreadLocal使用后务必调用remove方法。
 *    最简单有效的方法是使用后将其移除。
 */
public class ThreadLocalId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return nextId.incrementAndGet();
        }
    };

    public static int get(){
        return threadId.get();
    }

    public static void remove(){
        threadId.remove();
    }
}
