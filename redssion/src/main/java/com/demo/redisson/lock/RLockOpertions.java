package com.demo.redisson.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author lz
 * @date 2021-11-06 11:17
 * @ClassName RLockOpertions.java
 * @Description 可重入锁操作
 *
 */
@Slf4j
@Component
public class RLockOpertions {

    @Autowired
    RedissonClient redissonClient;

    /**
     * 用锁得时候，它是对线程加锁的，释放锁的线程id得和加锁得线程id相同
     * @param lockKey
     */
    public void unlockSingle(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        // 1. 最常见的使用方法
        //lock.lock();
        // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
        //lock.lock(10, TimeUnit.SECONDS);
        // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
        //boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
        try {
            if (lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                //业务处理
            } else {
                Assert.isTrue(false, "排队中,请稍后重试!");
            }
        } catch (InterruptedException e) {
            Assert.isTrue(false, "请勿重复操作!");
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void  lock() throws InterruptedException{
        log.info("线程：{}，进入方法",Thread.currentThread().getName());
        RLock rLock = redissonClient.getLock("lock");
        //加锁：锁的有效期默认30秒
        rLock.lock();
        long timeToLive = rLock.remainTimeToLive();
        log.info("线程：{}，获得锁，锁存活时间：{}S",Thread.currentThread().getName(),timeToLive/1000);
        //休眠一下
        Thread.sleep(2000);
        //如果主线程未释放，且当前锁未调用unlock方法，则进入到watchDog机制
        //如果主线程未释放，且当前锁调用unlock方法，则直接释放锁
        rLock.unlock();
        log.info("线程：{}，释放锁",Thread.currentThread().getName());
    }

    public void  lockLaseTime() throws InterruptedException{
        log.info("线程：{}，进入方法",Thread.currentThread().getName());
        RLock rLock = redissonClient.getLock("lockLaseTime");
        //加锁 上面是默认30秒,
        //这里可以手动设置锁的有效时间，锁到期后会自动释放的
        rLock.lock(10,TimeUnit.SECONDS);
        long timeToLive = rLock.remainTimeToLive();
        log.info("线程：{}，获得锁，锁存活时间：{}S",Thread.currentThread().getName(),timeToLive/1000);
        //休眠一下
        Thread.sleep(2000);
        //如果主线程未释放，且当前锁未调用unlock方法，则锁到期后会自动释放的
        //如果主线程未释放，且当前锁调用unlock方法，则直接释放锁
        rLock.unlock();
        log.info("线程：{}，释放锁",Thread.currentThread().getName());
    }

    public void  tryLock() throws InterruptedException {
        log.info("线程：{}，进入方法",Thread.currentThread().getName());
        RLock rLock = redissonClient.getLock("tryLock");
        //tryLock()方法是有返回值的，它表示用来尝试获取锁，
        //如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false .
        boolean flag = rLock.tryLock();
        if (flag){
            long timeToLive = rLock.remainTimeToLive();
            log.info("线程：{}，获得锁，锁存活时间：{}S，加锁状态：{}",Thread.currentThread().getName(),timeToLive/1000,flag);
            //休眠一下
            Thread.sleep(2000);
            //如果主线程未释放，且当前锁未调用unlock方法，则进入到watchDog机制
            //如果主线程未释放，且当前锁调用unlock方法，则直接释放锁
            rLock.unlock();
            log.info("线程：{}，释放锁",Thread.currentThread().getName());
        }else {
            log.info("线程：{}，获得锁失败",Thread.currentThread().getName());
        }
    }

    public void  tryLockWaitTime() throws InterruptedException {
        log.info("线程：{}，进入方法",Thread.currentThread().getName());
        RLock rLock = redissonClient.getLock("tryLockWaitTime");
        //tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，
        //只不过区别在于这个方法在拿不到锁时会等待一定的时间，
        //在时间期限之内如果还拿不到锁，就返回false。如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
        boolean flag = rLock.tryLock(6, TimeUnit.SECONDS);
        if (flag){
            long timeToLive = rLock.remainTimeToLive();
            log.info("线程：{}，获得锁，锁存活时间：{}S，加锁状态：{}",Thread.currentThread().getName(),timeToLive/1000,flag);
            //休眠一下
            Thread.sleep(10000);
            //如果主线程未释放，且当前锁未调用unlock方法，则进入到watchDog机制
            //如果主线程未释放，且当前锁调用unlock方法，则直接释放锁
            rLock.unlock();
            log.info("线程：{}，释放锁",Thread.currentThread().getName());
        }else {
            log.info("线程：{}，获得锁失败",Thread.currentThread().getName());
        }

    }


    public void  tryLockleasTime() throws InterruptedException {
        log.info("线程：{}，进入方法",Thread.currentThread().getName());
        RLock rLock = redissonClient.getLock("tryLockleasTime");
        //比上面多一个参数，多添加一个锁的有效时间
        boolean flag = rLock.tryLock(11,10, TimeUnit.SECONDS);
        if (flag){
            long timeToLive = rLock.remainTimeToLive();
            log.info("线程：{}，获得锁，锁存活时间：{}S，加锁状态：{}",Thread.currentThread().getName(),timeToLive/1000,flag);
            //休眠一下
            Thread.sleep(6000);
            //如果主线程未释放，且当前锁未调用unlock方法，则锁到期后会自动释放的
            //如果主线程未释放，且当前锁调用unlock方法，则直接释放锁
            rLock.unlock();
            log.info("线程：{}，释放锁",Thread.currentThread().getName());
        }else {
            log.info("线程：{}，获得锁失败",Thread.currentThread().getName());
        }
    }

}