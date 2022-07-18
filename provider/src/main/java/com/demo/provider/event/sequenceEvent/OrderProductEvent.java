package com.demo.provider.event.sequenceEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 13:47
 * Description: 自定义事件
 */
@Data
@ToString
public class OrderProductEvent extends ApplicationEvent {

    /** 该类型事件携带的信息 */
    private String orderId;


    public OrderProductEvent(Object source,String orderId) {
        super(source);
        this.orderId = orderId;
    }
}
