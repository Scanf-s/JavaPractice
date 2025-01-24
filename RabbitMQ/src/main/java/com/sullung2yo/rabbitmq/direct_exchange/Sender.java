package com.sullung2yo.rabbitmq.direct_exchange;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    /**
     * Producer 역할을 수행하는 클래스
     */

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(DirectExchangeConfig.QUEUE_NAME, message); // Direct Exchange 방식
        System.out.println("Sent <" + message + ">");
    }
}
