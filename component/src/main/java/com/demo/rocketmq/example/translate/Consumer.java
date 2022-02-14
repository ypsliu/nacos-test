package com.demo.rocketmq.example.translate;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:52
 * Created with IntelliJ IDEA
 * Description: No Description
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        //1.创建消费者consumer,制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        //2.指定NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //3.订阅主题Topic和Tage
//        consumer.subscribe("base","*"); //消费topic下所有tag
        consumer.subscribe("transaction","*");
        //设置消费模式 负载均衡|广播模式
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                list.stream()
                        .forEach(
                        messageExt -> {
                            System.out.println(new String(messageExt.getBody()));
                        }
                );
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者
        consumer.start();
        System.out.println("consumer:启动成功");
    }
}
