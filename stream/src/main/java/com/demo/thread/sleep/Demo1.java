package com.demo.thread.sleep;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/25
 * Time: 15:48
 * Description: Thread.sleep
 */
public class Demo1 {
    public static void main(String[] args) {
        Thread thread = new Thread(
                ()->{
                    System.out.println("线程执行："+ LocalDateTime.now());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ok :"+ LocalDateTime.now());
                }
        );
        thread.start();
    }
}
