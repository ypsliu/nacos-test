package com.demo.thread.order;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/21
 * Time: 9:57
 * Description: 线程顺序执行 CountDownLatch
 * CountDownLatch是一种同步辅助，在AQS基础之上实现的一个并发工具类，让我们多个线程执行任务时，需要等待线程执行完成后，才能执行下面的语句，之前线程操作时是使用 Thread.join方法进行等待 。
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。它相当于是一个计数器，这个计数器的初始值就是线程的数量，每当一个任务完成后，计数器的值就会减一，当计数器的值为 0 时，表示所有的线程都已经任务了，然后在 CountDownLatch 上等待的线程就可以恢复执行接下来的任务。
 *
 */
public class OrderExecute2 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch1 = new CountDownLatch(0);

        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        CountDownLatch countDownLatch3 = new CountDownLatch(1);


        Thread t1 = new Thread(new Work(countDownLatch1, countDownLatch2),"线程1");
        Thread t2 = new Thread(new Work(countDownLatch2, countDownLatch3),"线程2");
        Thread t3 = new Thread(new Work(countDownLatch3, countDownLatch3),"线程3");

        t1.start();
        t2.start();
        t3.start();
    }

    static class Work implements Runnable {
        CountDownLatch cOne;
        CountDownLatch cTwo;

        public Work(CountDownLatch cOne, CountDownLatch cTwo) {
            super();
            this.cOne = cOne;
            this.cTwo = cTwo;
        }

        @Override
        public void run() {
            try {
                cOne.await();
                System.out.println("执行: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                cTwo.countDown();
            }
        }
    }
}
