package com.demo.rocketmq.example.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:52
 * Created with IntelliJ IDEA
 * Description: No Description
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        //1.创建消费者consumer,制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.指定NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //3.订阅主题Topic和Tage
        consumer.subscribe("order","*");
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println("当前线程："+Thread.currentThread().getName());
                list.stream()
                        .forEach(
                                messageExt -> {
                                    System.out.println(messageExt);
                                    Order order = JSON.parseObject(new String(messageExt.getBody())
                                            ,new TypeReference<Order>(){});
                                    System.out.println(order);
                                }
                        );
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //5.启动消费者
        consumer.start();
        System.out.println("order consumer2:启动成功");
    }
}
