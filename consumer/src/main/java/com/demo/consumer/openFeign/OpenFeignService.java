package com.demo.consumer.openFeign;

import com.demo.consumer.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/16
 * Time: 15:15
 * Description: No Description
 */
@FeignClient(value = "provider",
        fallback = OpenFeignFallBackService.class)
public interface OpenFeignService {

    @GetMapping("/demo/provider/order1/{id}")
    String get(@PathVariable("id")Integer id);

    /**
     * 参数默认是@RequestBody标注的，
     * 这里的@RequestBody可以不填
     * 方法名称任意
     */
    @PostMapping("/openfeign/provider/order2")
    Order pojoPost(@RequestBody Order order);

    /**
     * 必须要@RequestParam注解标注，且value属性必须填上参数名
     * 方法参数名可以任意，但是@RequestParam注解中的value属性必须和provider 中的参数名相同
     */
    @PostMapping("/openfeign/provider/order3")
    String paramPost(@RequestParam("id") String arg1, @RequestParam("name") String arg2);
}
