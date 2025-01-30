package com.sullung2yo.rabbitmq.controller;

import com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    private final SimpMessagingTemplate messagingTemplate;

    public StompController (SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send")
    public void sendMessage(NotificationMessage notificationMessage) {
        // Queue에서 수신한 메세지를 구독자들(Client)에 브로드캐스팅
        String message = notificationMessage.getMessage();
        System.out.println("[#] message = " + message);

        // 클라이언트에 메세지를 브로드캐스트
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
