package com.demo.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 16:01
 * Description: 子线程如何获取父线程ThreadLocal的值
 */
public class InheritableThreadLocalDemo {
    @Test
    public void test1() {
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


    /**
     * 确实InheritableThreadLocal能够实现父子线程间传递本地变量，但是.....
     * 但是你的程序如果采用线程池，则存在着线程复用的情况，这时就不一定能够实现父子线程间传递了
     * 因为在线程在线程池中的存在不是每次使用都会进行创建，InheritableThreadlocal是在线程初始化时intertableThreadLocals=true才会进行拷贝传递。
     * 所以若本次使用的子线程是已经被池化的线程，从线程池中取出线下进行使用，是没有经过初始化的过程，也就不会进行父子线程的本地变量拷贝。
     */
    @Test
    public void test2() throws InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();

        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        for(int i=0 ;i < 10; i++){
            inheritableThreadLocal.set("lzy  demo-"+i);
            Thread.sleep(1000L);
            CompletableFuture.runAsync(
                    ()-> System.out.println(inheritableThreadLocal.get()),executor
            );
        }
    }

    @Test
    public void test3() throws Exception{
        //单一线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //需要使用TtlExecutors对线程池包装一下
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        //TransmittableThreadLocal创建
        TransmittableThreadLocal<String> username = new TransmittableThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            username.set("lzy  demo-" + i);
            Thread.sleep(1000);
            CompletableFuture.runAsync(() -> System.out.println(username.get()), executorService);
        }
    }

}
