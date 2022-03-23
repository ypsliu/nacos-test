package com.demo.provider.controller;

import com.demo.common.HttpUtils.constant.status.BaseStatusCode;
import com.demo.common.HttpUtils.constant.status.IStatusCode;
import com.demo.common.HttpUtils.constant.target.ResponseDataBody;
import com.demo.provider.domain.User;
import com.demo.provider.targer.checkToken;
import com.demo.redisson.operation.RedissonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
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
    public IStatusCode set(){
        User user = new User(001L,"lzy","lzy",28,"石家庄","18203217137");
        redissonObject.setValue("test_user",user,-1L);
        return BaseStatusCode.SUCCESS;
    }

    @RequestMapping(value = "/get")
    public IStatusCode get(){
        User user = redissonObject.getValue("test_user");
        Assert.notNull(user,"用户不存在");
        log.info("get user from redis: "+user);
        return BaseStatusCode.SUCCESS;
    }


}
