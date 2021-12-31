package com.demo.consumer.controller;

import com.demo.consumer.sentinel.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/17
 * Time: 15:00
 * Description: No Description
 */
@RequestMapping("/sentinel")
@RestController
public class SentinelController {
    @Autowired
    private SentinelService sentinelService;

    @GetMapping("block")
    public String getBlockResult(){
        return sentinelService.getBlockResult();
    }

    @GetMapping("fallback")
    public String getFallBackResult(){
        return sentinelService.getFallBackResult();
    }

    @GetMapping("all")
    public String getAllResult(){
        return sentinelService.getAllResult();
    }
}
