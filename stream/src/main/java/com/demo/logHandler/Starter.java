package com.demo.logHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/7
 * Time: 9:35
 * Description: No Description
 */
public class Starter {
    public static void main1(String[] args) {
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());
        throw new RuntimeException();
    }
    public static void main2(String[] args) {
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());
        new Thread(
                ()->{
                    throw new RuntimeException();
                }
        ).start();
    }

    public static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());

        Thread task = new Thread(
                ()->{
                    System.out.println(10/0);
                }
        );

        executorService.execute(task);
        executorService.shutdown();
    }
}
