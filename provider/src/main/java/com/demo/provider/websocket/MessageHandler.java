package com.demo.provider.websocket;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/13
 * Time: 9:29
 * Description: No Description
 */
@Component
@SuppressWarnings("unchecked")
public class MessageHandler extends TextWebSocketHandler {
    private List<WebSocketSession> clients = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        clients.add(session);
        System.out.println("uri :" + session.getUri());
        System.out.println("连接建立: " + session.getId());
        System.out.println("current seesion: " + clients.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        clients.remove(session);
        System.out.println("断开连接: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        Map<String, String> map = JSONObject.parseObject(payload, HashMap.class);
        System.out.println("接受到的数据" + map);
        clients.forEach(s -> {
            try {
                System.out.println("发送消息给: " + session.getId());
                s.sendMessage(new TextMessage("服务器返回收到的信息," + payload));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
