package com.demo.rocketmq.example.deadLetterQueue;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/16
 * Time: 11:13
 * Description: 死信队列
 *
 * 修改 队列属性 perm 2 -> 6
 */
public class DLQconsumer {

    public static final String GROUP_NAME = "group1";

    /**
     * 这个的groupId 要和 正常的groupId 做区分 ，否则会负载均衡失败，导致消息异常消费
     *
     * 如果同一个 GroupID 下的不同消费者实例，订阅了不同的 Topic+Tag
     * 将导致在对Topic 的消费队列进行负载均衡的时候产生不正确的结果，最终导致消息丢失。
     *
     */
    public static final String DLQ_GROUP_NAME = GROUP_NAME + "_dlq";

    public static final String DLQ_TOPIC = "%DLQ%"+GROUP_NAME;

    public static void main(String[] args) throws Exception {
        //1.创建消费者consumer,制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DLQ_GROUP_NAME);
        //2.指定NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //3.订阅主题Topic和Tage
//        consumer.subscribe("base","*"); //消费topic下所有tag
        consumer.subscribe(DLQ_TOPIC,"*");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //重试次数设置 默认的重试次数是16次 daley等级从3开始3-18 设置为1 失败不重试
        consumer.setMaxReconsumeTimes(1);
        //设置消费模式
//        consumer.setMessageModel();
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt:list) {
                    try {
                        System.out.println(messageExt);
                        System.out.println("DLQ consumer:"+new String(messageExt.getBody()));
                        int i = 1/0;
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }catch (Exception e){
                        /**
                         * 这里messageExt 消费重试次数会继承之前消费次数
                         */
                        if(messageExt.getReconsumeTimes() >= 5){
                            //最多重试 3次
                            System.out.println("max reconsume times :"+ new String(messageExt.getBody()));
                            return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        return  ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });
        //5.启动消费者
        consumer.start();
        System.out.println("死信队列consumer:启动成功");
    }
}
