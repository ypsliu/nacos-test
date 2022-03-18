package com.demo.limit;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 19:35
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<20;i++){
            new Thread(
                    ()->{
                        System.out.println(LimitManager.getInstance().limiting("www.baidu.com"));
                    }
            ).start();
        }
        TimeUnit.SECONDS.sleep(1L);
        LimitManager.getInstance().limiting("www.baidu.com");
        TimeUnit.SECONDS.sleep(1L);
        LimitManager.getInstance().limiting("www.baidu.com");
        TimeUnit.SECONDS.sleep(1L);
        LimitManager.getInstance().limiting("www.baidu.com");
        LimitManager.getInstance().limiting("www.baidu.com");

    }

    public static void main1(String[] args) {

    }
}
