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
        // Stomp 경로로 전달된 메세지의 경우에 대한 엔드포인트
        // 브라우저에서 Form으로 메세지 전송 시 /app/send가 호출되는데,
        // 이걸 처리하는 메서드가 바로 이 메서드이다.
        // 이건 RabbitMQ랑 관련 X -> 일반적인 WebSocket
        String message = notificationMessage.getMessage();
        System.out.println("[#] message = " + message);

        // WebSocket 경로로 메시지를 전달
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
