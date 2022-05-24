package com.demo.provider.controller;

import com.demo.common.HttpUtils.constant.status.BaseStatusCode;
import com.demo.common.HttpUtils.constant.status.IStatusCode;
import com.demo.common.HttpUtils.constant.target.ResponseDataBody;
import com.demo.common.HttpUtils.entity.response.BaseResponseDto;
import com.demo.provider.entity.User;
import com.demo.provider.targer.checkToken;
import com.demo.redisson.operation.RedissonObject;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/test")
    public BaseResponseDto test(){
        User user = redissonObject.getValue("test_user");
        Assert.notNull(user,"用户不存在");
        log.info("get user from redis: "+user);
        //js的number类型有个最大安全值，即2的53次方（9007199254740992），超过这个值就会出现精度丢失的问题
        user.setId(Long.MAX_VALUE);
        return new BaseResponseDto<>(BaseStatusCode.SUCCESS,user);
    }

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };
    @RequestMapping(value = "/thread")
    public IStatusCode threadLocal(){
        log.info("{} -> {}", Thread.currentThread().getName(), threadLocal.get()+1);
        threadLocal.remove();
        return BaseStatusCode.SUCCESS;
    }
    private ThreadLocal<Integer> i = new ThreadLocal<>();
    @RequestMapping(value = "/thread1")
    public IStatusCode threadLocal1(){
        if (i.get() == null) {
            i.set(0);
        }
        i.set(i.get() + 1);
        log.info("{} -> {}", Thread.currentThread().getName(), i.get());
        i.remove();
        return BaseStatusCode.SUCCESS;
    }
}
