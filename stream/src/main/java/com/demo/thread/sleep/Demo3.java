package com.demo.thread.sleep;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/25
 * Time: 15:53
 * Description: wait/notify/notifyAll
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        new Thread(
                ()->{
                    synchronized (lock){
                        try {
                            System.out.println("start:"+ LocalDateTime.now());
                            lock.wait(1000L);
                            System.out.println("wait:"+ LocalDateTime.now());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        synchronized (lock){
            lock.notify();
            System.out.println("end:"+LocalDateTime.now());
        }
    }
}
