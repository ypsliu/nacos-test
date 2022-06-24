package com.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/23
 * Time: 16:25
 * Description: No Description
 */
public class DaemonDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis());
        });
        thread.setDaemon(true);
        executorService.scheduleWithFixedDelay(thread,1L,1l, TimeUnit.SECONDS);


        //TODO 守护线程 ？
    }
}
