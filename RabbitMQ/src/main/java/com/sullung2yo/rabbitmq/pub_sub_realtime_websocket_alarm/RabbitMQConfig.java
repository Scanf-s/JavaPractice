package com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "notification";
    public static final String FANOUT_EXCHANGE = "notification";

    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        // 모든 큐로 작업 전송 (Fanout Exchange)
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindNotification(Queue notificationQueue, FanoutExchange fanoutExchange) {
        // Fanout Exchange와 큐를 연결(Binding)
        return BindingBuilder.bind(notificationQueue).to(fanoutExchange);
    }
}
