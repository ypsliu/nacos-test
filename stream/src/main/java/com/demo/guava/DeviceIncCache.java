package com.demo.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/27
 * Time: 9:45
 * Description: 本地缓存使用Guava Cache
 *
 * 缺点：
 * 增加编码复杂度，不直接
 * 只适用于缓存内容只增不改的场景
 *
 */
public class DeviceIncCache {
    /**
     * 本地缓存
     */
    private  Cache<String, Integer> localCache = CacheBuilder.newBuilder()
            .concurrencyLevel(16) // 并发级别
            .initialCapacity(1000) // 初始容量
            .maximumSize(10000) // 缓存最大长度
            .expireAfterAccess(1, TimeUnit.HOURS) // 缓存1小时没被使用就过期
            .build();
}
