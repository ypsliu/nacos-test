package com.demo.mapStruct.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/26
 * Time: 14:24
 * Description: No Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    private String name;

    private int age;

    private Date createTime;
}
