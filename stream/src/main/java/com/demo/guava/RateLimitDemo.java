package com.demo.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/10
 * Time: 10:03
 * Description: guava 令牌桶测试
 */
public class RateLimitDemo {
    //创建一个限流器，每秒产生2个令牌
    private static RateLimiter rateLimiter = RateLimiter.create(2);

    public static void main(String[] args) {
        /*第一次请求，“预支”了后面的令牌，而预支的时间，由下一次请求来“承受”*/
        double timeSpendOne = rateLimiter.acquire(10);
        System.out.println("第一次请求等待时长："+timeSpendOne);
        double timeSpendTwo = rateLimiter.acquire(2);
        System.out.println("第二次请求等待时长："+timeSpendTwo);
        double timeSpendThree = rateLimiter.acquire(2);
        System.out.println("第三次请求等待时长："+timeSpendThree);
    }
}
