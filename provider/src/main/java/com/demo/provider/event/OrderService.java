package com.demo.provider.event;

import com.demo.provider.event.asyncEvent.MsgEvent;
import com.demo.provider.event.sequenceEvent.OrderProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 13:51
 * Description: 定义发布者
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    /** 注入ApplicationContext用来发布事件 */
    private final ApplicationContext applicationContext;

    /**
     * 下单
     *
     * @param orderId 订单ID
     */
    public String buyOrder(String orderId) {
        long start = System.currentTimeMillis();
        // 1.查询订单详情

        // 2.检验订单价格 （同步处理）
        applicationContext.publishEvent(new OrderProductEvent(this, orderId));

        // 3.短信通知（异步处理）
        applicationContext.publishEvent(new MsgEvent(orderId));

        long end = System.currentTimeMillis();
        log.info("任务全部完成，总耗时：({})毫秒", end - start);
        return "购买成功";
    }
}
