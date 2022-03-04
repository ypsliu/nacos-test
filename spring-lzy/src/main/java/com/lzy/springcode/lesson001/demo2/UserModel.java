package com.lzy.springcode.lesson001.demo2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/2
 * Time: 14:53
 * Description: No Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String name;
    private int age;
}
