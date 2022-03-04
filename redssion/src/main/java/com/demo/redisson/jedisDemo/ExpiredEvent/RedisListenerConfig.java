package com.demo.redisson.jedisDemo.ExpiredEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/28
 * Time: 10:05
 * Description: 定义配置 RedisListenerConfig 实现监听 Redis key 过期时间
 */
//@Configuration
public class RedisListenerConfig {
//    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
