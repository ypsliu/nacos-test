package com.demo.mapStruct.logic;

import com.demo.mapStruct.dto.UserDTO;
import com.demo.mapStruct.mapper.UserMapper;
import com.demo.mapStruct.vo.UserVO;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/26
 * Time: 17:20
 * Description: No Description
 */
public class Logic {
    public static void main(String[] args) {
        UserDTO userDTO = UserDTO.builder()
                .loginName("张三")
                .age(10)
                .createTime(LocalDateTime.now())
                .build();
        UserVO userVO = UserMapper.INSTANCE.userVO2UserDTO(userDTO);
        System.out.println(userVO);
    }
}
