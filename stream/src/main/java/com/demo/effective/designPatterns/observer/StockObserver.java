package com.demo.effective.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:23
 * Description: No Description
 */
public class StockObserver implements Observer{

    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}
