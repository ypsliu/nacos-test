package com.demo.consumer.controller;

import com.demo.consumer.config.NacosConfig;
import com.demo.consumer.config.SharedNacosConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/consumer")
@RestController
@Slf4j
public class ConsumerController {
    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private NacosConfig nacosConfig;

    @Autowired
    private SharedNacosConfig sharedNacosConfig;

    @Value("${server-url.nacos-provider}")
    private  String url;


    @GetMapping("getProvider/{id}")
    public String getProvider(@PathVariable("id")Integer id){
        return restTemplate.getForObject(url+"/demo/provider/getIp/"+id,String.class)
                +",version: "+nacosConfig.getVersion()
                +",user: "+sharedNacosConfig.getUser()
                +",password: "+sharedNacosConfig.getPasswrod();
    }
}
