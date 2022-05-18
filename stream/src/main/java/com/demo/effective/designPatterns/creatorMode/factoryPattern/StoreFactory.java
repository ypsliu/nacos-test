package com.demo.effective.designPatterns.creatorMode.factoryPattern;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 10:24
 * Description: No Description
 */
public class StoreFactory {
    public ICommodity getCommodityService(Integer commodityType) {
        if (null == commodityType) return null;
        if (1 == commodityType) return new CouponCommodityService();
        if (2 == commodityType) return new GoodsCommodityService();
        if (3 == commodityType) return new CardCommodityService();
        throw new RuntimeException("不存在的商品服务类型");
    }

    public static void main(String[] args) throws Exception {
        StoreFactory storeFactory = new StoreFactory();
        ICommodity iCommodity = storeFactory.getCommodityService(1);
        iCommodity.sendCommodity("10086","1008601","10086001",new HashMap<>());
        /**
         * 避免创建者与具体的产品逻辑耦合 、 满⾜单⼀职责，每⼀个业务逻辑实现都在所属⾃⼰的类中完成 、
         * 满⾜开闭原则，⽆需更改使⽤调⽤⽅就可以
         * 在程序中引⼊新的产品类型 。但这样也会带来⼀些问题，⽐如有⾮常多的奖品类型，那么实现的⼦
         * 类会极速扩张。因此也需要使⽤其他的模式进⾏优化，这些在后续的设计模式中会逐步涉及到。
         */
    }
}
