package com.demo.effective.designPatterns.template;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:00
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        AbstractPushTemplate template1 = new PushCouponTemplate();
        template1.push(1, "糖果店");

        AbstractPushTemplate template2 = new PushScoreTemplate();
        template2.push(1, "服装店");
    }
}
