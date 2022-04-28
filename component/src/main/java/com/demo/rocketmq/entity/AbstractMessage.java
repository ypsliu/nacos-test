package com.demo.rocketmq.entity;

import com.demo.rocketmq.constant.MQSendTypeEnum;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/21
 * Time: 14:10
 * Description: No Description
 */
public abstract class AbstractMessage {
    /** MQ名称 **/
    public abstract String getMQName();

    /** MQ 类型 **/
    public abstract MQSendTypeEnum getMQType();

    /** 构造MQ消息体 String类型 **/
    public abstract String toMessage();
}
