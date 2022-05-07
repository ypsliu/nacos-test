package com.demo.effective.designPatterns.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:52
 * Description: 使用策略的辅助"容器"
 */
public class OrderServiceExecutor {
    private final OrderService orderService;

    public OrderServiceExecutor(OrderService orderService) {
        this.orderService = orderService;
    }

    public void save(String orderNo) {
        this.orderService.saveOrder(orderNo);
    }
}
