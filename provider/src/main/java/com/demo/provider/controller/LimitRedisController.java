package com.demo.provider.controller;

import com.demo.provider.redis.limiting.RedisLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 9:26
 * Description: No Description
 */
@Slf4j
@RestController
@RequestMapping("/limit/redis")
public class LimitRedisController {
    /**
     * 基于Redis AOP限流
     */
    @GetMapping("/test")
    @RedisLimit(key = "redis-limit:test", permitsPerSecond = 2, expire = 1, msg = "当前排队人数较多，请稍后再试！")
    public String test() {
        log.info("限流成功。。。");
        return "ok";
    }
}
