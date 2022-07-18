package com.demo.provider.event.sequenceEvent;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 13:49
 * Description: 实现 ApplicationListener 接口，并指定监听的事件类型
 *
 *  监听并处理事件，实现 ApplicationListener 接口或者使用 @EventListener 注解
 */
@Slf4j
@Component
public class OrderProductListener implements ApplicationListener<OrderProductEvent> {
    /** 使用 onApplicationEvent 方法对消息进行接收处理 */
    @SneakyThrows
    @Override
    public void onApplicationEvent(OrderProductEvent orderProductEvent) {
        String orderId = orderProductEvent.getOrderId();
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("{}：校验订单商品价格耗时：({})毫秒", orderId, (end - start));
    }
}
