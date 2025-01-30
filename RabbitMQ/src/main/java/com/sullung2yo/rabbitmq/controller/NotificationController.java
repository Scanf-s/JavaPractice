package com.sullung2yo.rabbitmq.controller;

import com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm.NotificationMessage;
import com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm.NotificationPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    /**
     * 구독 요청을 보내는 REST API Controller
     */

    private final NotificationPublisher publisher;

    public NotificationController(NotificationPublisher notificationPublisher) {
        this.publisher = notificationPublisher;
    }

    @PostMapping
    public String sendNotification(@RequestBody NotificationMessage message) {
        publisher.publish(message.getMessage());
        return "Successfully sent message : " + message.getMessage();
    }

}
