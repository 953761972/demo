package com.example.demo.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("我接收到的消息："+payload);

        String rtnMsg = "我回复了";

        for (int i=0;i<10;i++) {
            Thread.sleep(2000);
            session.sendMessage(new TextMessage(rtnMsg+i));
        }
        super.handleTextMessage(session, message);
    }
}
