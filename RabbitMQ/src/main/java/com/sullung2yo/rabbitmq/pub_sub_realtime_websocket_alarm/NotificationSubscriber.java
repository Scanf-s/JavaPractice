package com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubscriber {

    // 클라이언트 측에서 구독할 경로 (WebSocket을 통해 메시지를 전달받을 때 사용하는 주소)
    private static final String CLIENT_URL = "/topic/notifications";
    private final SimpMessagingTemplate simpMessagingTemplate; // 특정 경로에 메세지를 전달할 때 사용

    public NotificationSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void subscriber(String message) {
        // "notification"이라고 이름이 붙여진 큐(RabbitMQ)에서 메시지를 수신할 때 호출되는 메서드
        // 즉, Subscriber이다.
        System.out.println("[#] Received Notification: " + message);

        // WebSocket 경로로 메시지를 전달 (모든 구독자에게 전송)
        simpMessagingTemplate.convertAndSend(CLIENT_URL, message);
    }
}
