package com.demo.thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/25
 * Time: 16:19
 * Description: LockSupport 是更加底层的操作线程休眠和唤醒的对象，它提供了两个常用的方法：
 *
 *      LockSupport.park()：休眠当前线程。
 *      LockSupport.unpark(Thread thread)：唤醒一个指定的线程。
 */
public class Demo5 {
    public static void main(String[] args) {
        Thread t1 = new Thread(
                ()->{
                    System.out.println("线程1休眠");
                    LockSupport.park();
                    System.out.println("线程1执行结束");
                },"线程1"
        );
        t1.start();


        Thread t2 = new Thread(
                ()->{
                    System.out.println("线程2休眠");
                    LockSupport.park();
                    System.out.println("线程2执行结束");
                },"线程2"
        );
        t2.start();

        Thread t3 = new Thread(
                ()->{
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                        LockSupport.unpark(t2);

                        TimeUnit.SECONDS.sleep(2L);
                        LockSupport.unpark(t1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },"线程3"
        );
        t3.start();

    }
}
