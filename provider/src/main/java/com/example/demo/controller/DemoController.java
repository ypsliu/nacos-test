package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.handler.ResponseDataBody;
import com.example.demo.redis.RedissonObject;
import com.example.demo.targer.checkToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lz
 * @date 2021-08-18 11:18
 */
@checkToken
@RestController
@RequestMapping("/redis")
@ResponseDataBody
@Slf4j
public class DemoController {
    @Resource
    private RedissonObject redissonObject;

    @RequestMapping(value = "/set")
    public String set(){
        User user = new User(001L,"lzy","lzy",28,"石家庄","18203217137");
        redissonObject.setValue("test_user",user,-1L);
        return "hello world get";
    }

    @RequestMapping(value = "/get")
    public String get(){
        User user = redissonObject.getValue("test_user");
        log.info("get user from redis: "+user);
        return "hello world set";
    }


}
