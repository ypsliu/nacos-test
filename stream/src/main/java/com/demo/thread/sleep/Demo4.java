package com.demo.thread.sleep;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/25
 * Time: 16:03
 * Description: No Description
 */
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        //创建锁
        final Lock lock = new ReentrantLock();
        //创建Condition
        final Condition condition = lock.newCondition();
        new Thread(
                ()->{
                    System.out.println("start:"+ LocalDateTime.now());
                    //创建锁
                    lock.lock();
                    try {
                        //线程休眠
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //释放锁
                        lock.unlock();
                    }
                    System.out.println("end:"+LocalDateTime.now());
                }
        ).start();

        Thread.sleep(1000);

        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }

    }
}
