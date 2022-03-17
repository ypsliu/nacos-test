package com.demo.rocketmq.example.delay;

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
 * Description: 延时消费生产者
 *
 * messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h" 18个等级
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3.启动producer
        producer.start();

        //4.创建消息对象，指定主题topic,tag和消息体
        for(int i=1;i<=10;i++){
            Message msg = new Message("delay", "delay", ("hello world"+i).getBytes(StandardCharsets.UTF_8));
            //设置消息延迟
            msg.setDelayTimeLevel(2);
            //5.发送消息
            SendResult result = producer.send(msg);
            //结果数据获取
            SendStatus status = result.getSendStatus();
            String msgId = result.getMsgId();
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送状态："+result
                    +",消息编号："+msgId+",队列编号："+queueId);

        }

        //6.关闭生产者producer
        producer.shutdown();
    }
}
