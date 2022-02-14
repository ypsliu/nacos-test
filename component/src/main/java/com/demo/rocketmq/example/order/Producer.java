package com.demo.rocketmq.example.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3.启动producer
        producer.start();

        //创建订单消息
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1000L,"创建");
        orders.add(order);
        Order order1 = new Order(1000L,"付款");
        orders.add(order1);
        Order order2 = new Order(1000L,"配送");
        orders.add(order2);
        Order order3 = new Order(1000L,"完成");
        orders.add(order3);
        Order order4 = new Order(1001L,"创建");
        orders.add(order4);
        Order order5 = new Order(1001L,"删除");
        orders.add(order5);
        //4.创建消息对象，指定主题topic,tag和消息体
        for(Order o:orders){
            Message msg = new Message("order", "order"
                    , JSON.toJSONString(o).getBytes(StandardCharsets.UTF_8));
            //普通
//            producer.send(msg);
            //消息队列选择器 按照orderId选择broker内部queue
            producer.send(msg, new SelectMessageQueueByHash(),o.getOrderId());
        }

        //6.关闭生产者producer
        producer.shutdown();
    }
}
