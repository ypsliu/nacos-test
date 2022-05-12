package com.demo.redisson.jedisDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/12
 * Time: 23:12
 * Created with IntelliJ IDEA
 * Description: No Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String productName;
}
