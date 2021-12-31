package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lz
 * @date 2021-08-19 14:45
 */
@Component
public class DemoService1 {
    @Resource
    private UserMapper userMapper ;

    @Transactional(rollbackFor = Exception.class , propagation = Propagation.REQUIRES_NEW)
    public void insert1(){
        int i = 1/0;
        User user = new User();
        user.setId(5L);
        user.setUserName("周杰伦");
        user.setAge(39);

        userMapper.insert(user);
    }
}
