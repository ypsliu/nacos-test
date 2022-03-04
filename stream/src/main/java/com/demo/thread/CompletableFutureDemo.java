package com.demo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/28
 * Time: 14:12
 * Description: No Description
 * 可以实现如下等功能
 *
 * 转换（thenCompose）
 *
 * 组合（thenCombine）
 *
 * 消费（thenAccept）
 *
 * 运行（thenRun）。
 *
 * 带返回的消费（thenApply）
 */
public class CompletableFutureDemo {
    public static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        CompletableFuture completableFuture =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("current time:"+System.currentTimeMillis());
                    try {
                        Thread.sleep(2000L);
                    }catch (Exception e){

                    }
                    System.out.println("then time:"+System.currentTimeMillis());
                    System.out.println("supplyAsync " + Thread.currentThread().getName());
                    return "hello";
                }, executorService)
                        .thenApplyAsync(s->{
                            System.out.println(s+" world");
                            return "hhh";
                        },executorService);

        completableFuture.thenRunAsync(()->{
            System.out.println(Thread.currentThread().getName()+"|ddd");
        });

        completableFuture.thenRun(()->{
            System.out.println(Thread.currentThread().getName()+"|dddsd");
        });

        completableFuture.thenRun(()->{
            System.out.println(Thread.currentThread().getName()+"|dddsadad");
        });
//        executorService.shutdown();
    }
}
