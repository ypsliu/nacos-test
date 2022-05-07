package com.demo.effective.designPatterns.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:51
 * Description: No Description
 */
public class MySqlSaveOrderStrategy implements OrderService{
    @Override
    public void saveOrder(String orderNo) {
        System.out.println("order:" + orderNo + " save to mysql");
    }
}
