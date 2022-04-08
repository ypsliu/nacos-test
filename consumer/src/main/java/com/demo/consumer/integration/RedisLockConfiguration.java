package com.demo.consumer.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/6
 * Time: 9:44
 * Description: 注意，如果使用新版Springboot进行集成时需要使用Redis4版本，否则会出现下面的异常告警，
 * 主要是 unlock() 释放锁时使用了UNLINK命令，这个需要Redis4版本才能支持。
 *
 * The UNLINK command has failed (not supported on the Redis server?); falling back to the regular DELETE command
 * org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR unknown command 'UNLINK'
 */
@Configuration
public class RedisLockConfiguration {
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory){
        return new RedisLockRegistry(redisConnectionFactory, "redis-lock");
    }
}
