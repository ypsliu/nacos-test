package com.demo.effective.designPatterns.template;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:58
 * Description: 模板模式 - 抽象模板类
 */
public abstract class AbstractPushTemplate {

    abstract protected void execute(int customerId, String shopName);

    public void push(int customerId, String shopName) {
        System.out.println("准备推送...");
        execute(customerId, shopName);
        System.out.println("推送完成\n");
    }

}
