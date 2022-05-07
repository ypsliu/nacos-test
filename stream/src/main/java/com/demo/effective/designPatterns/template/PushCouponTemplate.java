package com.demo.effective.designPatterns.template;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:59
 * Description: 优惠券的具体模板
 */
public class PushCouponTemplate extends AbstractPushTemplate{
    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员:" + customerId + ",你好，" + shopName + "送您一张优惠券");
    }
}
