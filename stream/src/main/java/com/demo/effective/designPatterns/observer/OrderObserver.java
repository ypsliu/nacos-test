package com.demo.effective.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:22
 * Description: No Description
 */
public class OrderObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 状态更新为【已支付】");
    }
}
