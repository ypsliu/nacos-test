package com.demo.effective.designPatterns.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:53
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor(new MySqlSaveOrderStrategy());
        executor1.save("001");
        OrderServiceExecutor executor2 = new OrderServiceExecutor(new NoSqlSaveOrderStrategy());
        executor2.save("002");
    }
}
