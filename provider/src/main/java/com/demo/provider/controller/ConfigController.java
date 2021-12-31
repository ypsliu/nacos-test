package com.demo.provider.controller;

import com.demo.provider.constant.BaseStatusCode;
import com.demo.provider.constant.IStatusCode;
import com.demo.provider.handler.ResponseDataBody;
import com.demo.provider.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/14
 * Time: 11:03
 * Description: No Description
 */
@RequestMapping("/config")
@RestController
@ResponseDataBody
@Slf4j
public class ConfigController {
    @Autowired
    private ConfigUtil configUtil;
    @GetMapping("get")
    public IStatusCode getConfigs(){
        log.info("name: {},title: {},desc: {},value: {},number: {},bignumber: {},test1: {},test2 {}",
                configUtil.getName(),configUtil.getTitle(),configUtil.getDesc(),
                configUtil.getValue(),configUtil.getNumber(),configUtil.getBignumber(),
                configUtil.getTest1(),configUtil.getTest2());
        return BaseStatusCode.SUCCESS;
    }
}
