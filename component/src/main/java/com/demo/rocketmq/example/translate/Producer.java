package com.demo.rocketmq.example.translate;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:11
 * Created with IntelliJ IDEA
 * Description: 发送同步消息
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        //1.创建消息生产者producer,并制定生产者组名
        TransactionMQProducer producer = new TransactionMQProducer("group2");
        //2.指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3.设置事务监听器
        TransactionListener transactionListener = new TransactionListener() {
            //执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if("tag1".equals(message.getTags())){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                else if("tag2".equals(message.getTags())){
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                else if("tag3".equals(message.getTags())){
                    return LocalTransactionState.UNKNOW;
                }else{
                    return null;
                }
            }
            //mq进行消息事务状态回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("消息tag:"+messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };
        producer.setTransactionListener(transactionListener);
        //3.启动producer
        producer.start();

        String[] tags = {"tag1","tag2","tag3"};
        //4.创建消息对象，指定主题topic,tag和消息体
        for(int i=0;i<3;i++){
            Message msg = new Message("transaction", tags[i], ("hello world"+i).getBytes(StandardCharsets.UTF_8));
            //5.发送消息
            SendResult result = producer.sendMessageInTransaction(msg,null);
            //结果数据获取
            SendStatus status = result.getSendStatus();
            String msgId = result.getMsgId();
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送状态："+result
                    +",消息编号："+msgId+",队列编号："+queueId);

            //线程睡一秒
            TimeUnit.SECONDS.sleep(1);
        }

        //6.关闭生产者producer 需要回查 暂不关闭
//        producer.shutdown();
    }
}
