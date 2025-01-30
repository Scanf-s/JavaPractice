package com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationPublisher {
    /*
     * - Publisher는 지정된 RabbitMQ Exchange로 메세지를 보내는 역할
     * - Fanout 방식으로 할 것이므로, 연결된 모든 큐에 메세지를 브로드캐스팅 한다
     */

    private final RabbitTemplate rabbitTemplate;

    public NotificationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(String message) {
        // API로 POST /notification 요청 보내는 경우
        // Fanout 방식은 구독한 모든 큐에 메세지를 전송하는 방식이므로 routingKey가 필요가 없음
        // convertAndSend로 RabbitMQ의 Exchange로 메세지 전달
        // 즉, 구독한 모든 Client들에게 API로 보낸 메세지가 전달된다.
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
        System.out.println("[#] Published Notification: " + message);
    }
}
