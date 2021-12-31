package com.demo.consumer.controller;

import com.demo.consumer.config.NacosConfig;
import com.demo.consumer.config.SharedNacosConfig;
import com.demo.consumer.openFeign.OpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/15
 * Time: 13:40
 * Description: No Description
 */
@RequestMapping("/openFeign/consumer")
@RestController
@Slf4j
public class OpenFeignController {
    @Autowired
    private OpenFeignService openFeignService;

    @GetMapping("order1/{id}")
    public String order1(@PathVariable("id") Integer id){
        return openFeignService.get(id);
    }
}
