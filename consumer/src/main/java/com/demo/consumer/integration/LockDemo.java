package com.demo.consumer.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/6
 * Time: 9:50
 * Description: No Description
 */
@Slf4j
public class LockDemo {
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    public void test() {
        Lock lock = redisLockRegistry.obtain("redis");
        try{
            //尝试在指定时间内加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                log.info("lock is ready");
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            log.error("obtain lock error",e);
        } finally {
            lock.unlock();
        }
    }
}
