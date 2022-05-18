package com.demo.effective.designPatterns.creatorMode.factoryPattern;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 10:22
 * Description: 实物商品
 */
@Slf4j
public class GoodsCommodityService implements ICommodity{
    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        log.info("请求参数[实物商品] => uId：{} commodityId：{} bizId：{} extMap：{}"
                , uId, commodityId, bizId, JSON.toJSON(extMap));
    }
}
