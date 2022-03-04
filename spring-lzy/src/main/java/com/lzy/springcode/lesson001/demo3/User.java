package com.lzy.springcode.lesson001.demo3;

import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/2
 * Time: 16:11
 * Description: No Description
 */
@Data
@Builder
public class User {
    private String name;
    private String age;
    public User(){
        this.name = "我是通过无参构造器创建的";
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
