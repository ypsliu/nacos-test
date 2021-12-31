package com.demo.consumer.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/17
 * Time: 16:55
 * Description: sentinel 兜底类
 */
@Slf4j
public class BlockHandler {

    /**
     * 兜底方法必须是public 返回类型与原方法一致
     * @param blockException
     * @return
     */
    public static String handler(BlockException blockException){
        log.info("block handler {}",blockException);
        return "block handler "+blockException.getRule();
    }
}
