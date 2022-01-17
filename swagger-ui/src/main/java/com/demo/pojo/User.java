package com.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/17
 * Time: 17:48
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {
    @ApiModelProperty(name="name",value="姓名",required=true)
    private String name;
    @ApiModelProperty(name="password",value="密码",required=true)
    private String password;
    @ApiModelProperty(name="age",value="年龄",required=true,dataType="int")
    private int age;
}
