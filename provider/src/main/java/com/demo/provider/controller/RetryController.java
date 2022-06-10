package com.demo.provider.controller;

import com.demo.common.HttpUtils.constant.status.BaseStatusCode;
import com.demo.common.HttpUtils.constant.status.IStatusCode;
import com.demo.common.HttpUtils.constant.target.ResponseDataBody;
import com.demo.provider.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/10
 * Time: 13:52
 * Description: No Description
 */
@Slf4j
@RestController
@ResponseDataBody
@RequestMapping("/retry")
public class RetryController {
    @Autowired
    private RetryService retryService;

    @GetMapping("test")
    public int test() throws Exception {
        return retryService.retry(0);
    }
}
