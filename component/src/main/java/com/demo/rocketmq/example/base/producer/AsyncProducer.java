package com.demo.rocketmq.example.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:37
 * Created with IntelliJ IDEA
 * Description: 异步发送消息
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception{
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3.启动producer
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(1);
        //4.创建消息对象，指定主题topic,tag和消息体
        for(int i=1;i<=10;i++){
            Message msg = new Message("base", "tag2", ("hello world"+i).getBytes(StandardCharsets.UTF_8));
           producer.send(msg, new SendCallback() {
               @Override
               public void onSuccess(SendResult result) {
                   //结果数据获取
                   SendStatus status = result.getSendStatus();
                   String msgId = result.getMsgId();
                   int queueId = result.getMessageQueue().getQueueId();
                   System.out.println("发送状态："+result
                           +",消息编号："+msgId+",队列编号："+queueId);
               }

               @Override
               public void onException(Throwable throwable) {

               }
           });

            //线程睡一秒
            TimeUnit.SECONDS.sleep(1);
        }

        //6.关闭生产者producer
        producer.shutdown();
    }
}
