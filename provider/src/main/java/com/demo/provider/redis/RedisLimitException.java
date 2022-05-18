package com.demo.provider.redis;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 9:24
 * Description: Redis限流自定义异常
 */
public class RedisLimitException extends RuntimeException{
    public RedisLimitException(String msg) {
        super( msg );
    }
}
