package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lz
 * @date 2021-08-19 14:45
 */
@Component
public class DemoService {
    @Resource
    private UserMapper userMapper ;

    @Autowired
    private DemoService1 demoService1;

    @Transactional(rollbackFor = Exception.class)
    public void insert(){
        User user = new User();
        user.setId(4L);
        user.setAge(48);
        user.setUserName("黄家驹");
        user.setAddress("香港");
        userMapper.insert(user);
        try {
            demoService1.insert1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
