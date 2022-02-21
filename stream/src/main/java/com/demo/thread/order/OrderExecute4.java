package com.demo.thread.order;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/21
 * Time: 9:57
 * Description:
 * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法
 *
 */
public class OrderExecute4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Work(),"线程1");

        Thread t2 = new Thread(new Work(),"线程2");

        Thread t3 = new Thread(new Work(),"线程3");


        CompletableFuture
                .runAsync(()->t1.start())
                .thenRun(()->t2.start())
                .thenRun(()->t3.start());
    }

    static class Work implements Runnable{
        @Override
        public void run() {
            System.out.println("执行 : " + Thread.currentThread().getName());
        }
    }
}
