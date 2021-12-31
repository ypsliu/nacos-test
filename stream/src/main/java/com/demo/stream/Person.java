package com.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/10 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;
}
