package com.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lz
 * @date 2021-11-16 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dish {
    private  String name;
    private  boolean vegatarian;
    private  int calories;
    private Type type;
}
