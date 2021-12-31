package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/15
 * Time: 9:48
 * Description: No Description
 */
@RequestMapping("/provider")
@RestController
@Slf4j
public class NacosController {
    @GetMapping("getIp/{id}")
    public String getIp(@PathVariable("id") Integer id){
        return "127.0.0.1:"+id;
    }

    @GetMapping("order1/{id}")
    public String order1(@PathVariable("id") Integer id){
        System.out.println(1/0);
        return "localhost:"+id;
    }
}
