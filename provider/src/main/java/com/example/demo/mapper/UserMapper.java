package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.User;

import java.util.List;

/**
 * @author lz
 * @date 2021-08-17 15:27
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
}
