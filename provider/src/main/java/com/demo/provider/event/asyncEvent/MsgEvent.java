package com.demo.provider.event.asyncEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 13:58
 * Description: No Description
 */
@Data
@AllArgsConstructor
public class MsgEvent {
    /** 该类型事件携带的信息 */
    public String orderId;
}
