package com.demo.consumer.openFeign;

import com.demo.consumer.entity.Order;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/16
 * Time: 16:57
 * Description: 熔断回调类
 */
@Configuration
public class OpenFeignFallBackService implements OpenFeignService{
    @Override
    public String get(Integer id) {
        return "Trigger Sentinel";
    }

    @Override
    public Order pojoPost(Order order) {
        return null;
    }

    @Override
    public String paramPost(String arg1, String arg2) {
        return null;
    }
}
