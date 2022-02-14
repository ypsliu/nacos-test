package com.demo.rocketmq.example.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:11
 * Created with IntelliJ IDEA
 * Description: 单向消息发送，不关心发送结果，例如日志推送
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception{
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3.启动producer
        producer.start();

        //4.创建消息对象，指定主题topic,tag和消息体
        for(int i=1;i<=10;i++){
            Message msg = new Message("base", "tag3", ("hello world one way"+i).getBytes(StandardCharsets.UTF_8));
            //5.发送消息
            producer.sendOneway(msg);
            //线程睡一秒
            TimeUnit.SECONDS.sleep(1);
        }

        //6.关闭生产者producer
        producer.shutdown();
    }
}
