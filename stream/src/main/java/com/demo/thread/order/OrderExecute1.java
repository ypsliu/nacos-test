package com.demo.thread.order;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/21
 * Time: 9:57
 * Description: 线程顺序执行 join
 * 等待这个线程结束，也就是说当前线程等待这个线程结束后再继续执行
 *
 */
public class OrderExecute1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(
                ()->{
                    System.out.println("thread 1");
                }
        );

        Thread t2 = new Thread(
                ()->{
                    try {
                        t1.join();
                        System.out.println("thread 2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread t3 = new Thread(
                ()->{
                    try {
                        t2.join();
                        System.out.println("thread 3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        t1.start();
        t2.start();
        t3.start();
    }
}
