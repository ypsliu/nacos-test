package com.demo.dynamic.controller;

import com.demo.dynamic.entity.TenantInfo;
import com.demo.dynamic.service.TenantInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping
@RestController
@Slf4j
public class TenantInfoController {
    @Autowired
    private TenantInfoService tenantInfoService;
    @GetMapping("getTenantInfo/{id}")
    public String getIp(@PathVariable("id") Integer id){
        switch (id){
            case 1:
                log.info("tenantInfo_salve:{}",tenantInfoService.list_slave());
                break;
            case 2:
                log.info("tenantInfo_master:{}",tenantInfoService.list_master());
                break;
            case 3:
                log.info("tenantInfo_default:{}",tenantInfoService.list_default());
                break;
            default:
                log.error("a zhe");
                break;
        }
        return "ok";
    }

}
