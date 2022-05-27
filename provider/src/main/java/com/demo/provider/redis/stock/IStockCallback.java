package com.demo.provider.redis.stock;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/27
 * Time: 9:18
 * Description: 初始化库存回调函数
 */
public interface IStockCallback {
    /**
     * 获取库存
     * @return
     */
    int getStock();
}
