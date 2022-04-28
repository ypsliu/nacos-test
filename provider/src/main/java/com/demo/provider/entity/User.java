package com.demo.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author lz
 * @date 2021-08-17 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    private long id;
    private String userName;
    private String password;
    private int age;
    private String address;
    private String phoneNum;
}
