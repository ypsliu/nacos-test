package com.demo.thread.sleep;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/25
 * Time: 15:51
 * Description: TimeUnit
 */
public class Demo2 {
    public static void main(String[] args) {
        Thread thread = new Thread(
                ()->{
                    System.out.println("start:"+ LocalDateTime.now());
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end:"+LocalDateTime.now());
                }
        );
        thread.start();
    }
}
