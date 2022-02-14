package com.demo.rocketmq.example.order;

import lombok.*;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 16:38
 * Created with IntelliJ IDEA
 * Description: No Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long orderId;
    private String desc;
}
