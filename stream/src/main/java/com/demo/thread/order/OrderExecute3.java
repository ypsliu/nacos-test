package com.demo.thread.order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/21
 * Time: 9:57
 * Description: 在Executors 类中有个单线程池的创建方式
 *
 */
public class OrderExecute3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(
                ()->{
                    System.out.println("thread 1");
                }
        );

        Thread t2 = new Thread(
                ()->{
                    System.out.println("thread 2");
                }
        );

        Thread t3 = new Thread(
                ()->{
                    System.out.println("thread 3");
                }
        );

        ExecutorService executorService = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);

    }
}
