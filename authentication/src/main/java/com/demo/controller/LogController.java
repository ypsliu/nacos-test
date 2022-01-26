package com.demo.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/12
 * Time: 15:58
 * Description: No Description
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @GetMapping("async")
    public SaResult info() {
        Long startTime = System.currentTimeMillis();
        for(int i=0;i<5120;i++){
            if(log.isDebugEnabled()){
                log.debug(i+"i");
            }
            log.info("this is async log");//24957
        }
        return SaResult.ok("use time:"+(System.currentTimeMillis()-startTime));
    }

}
