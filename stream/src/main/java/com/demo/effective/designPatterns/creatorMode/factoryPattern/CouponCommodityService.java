package com.demo.effective.designPatterns.creatorMode.factoryPattern;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 10:17
 * Description: 优惠券
 */
@Slf4j
public class CouponCommodityService implements ICommodity{
    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}"
                , uId, commodityId, bizId, JSON.toJSON(extMap));
    }
}
