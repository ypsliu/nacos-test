package com.demo.effective.designPatterns.creatorMode.factoryPattern;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 10:16
 * Description: 工厂模式
 *
 * 所有的奖品⽆论是实物、虚拟还是第三⽅，都需要通过我们的程序实现此接⼝进⾏处理，以保证最
 * 终⼊参出参的统⼀性。
 * 接⼝的⼊参包括； ⽤户ID 、 奖品ID 、 业务ID 以及 扩展字段 ⽤于处理发放实物商品时的收获地
 * 址。
 */
public interface ICommodity {
    void sendCommodity(String uId, String commodityId, String bizId,
                       Map<String, String> extMap) throws Exception;
}
