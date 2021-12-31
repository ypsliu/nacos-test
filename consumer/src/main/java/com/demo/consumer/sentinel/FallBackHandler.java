package com.demo.consumer.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/17
 * Time: 16:55
 * Description: sentinel 熔断类
 */
@Slf4j
public class FallBackHandler {

    /**
     * 方法必须是public 返回类型与原方法一致
     * @param throwable
     * @return
     */
    public static String handler(Throwable throwable){
        log.info("fall back handler {}",throwable);
        return "fall back handler "+throwable.getMessage();
    }
}
