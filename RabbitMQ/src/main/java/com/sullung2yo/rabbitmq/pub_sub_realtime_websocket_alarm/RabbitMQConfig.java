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
        // RabbitMQ에서 사용하는 Queue 설정
        // 이전에 했던것과 마찬가지로, duration=true로 설정 (Spring Boot가 재시작해도 Queue가 유지됨)
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        // 라우팅 키와 상관 없이 연결된 모든 큐에 메세지 브로드캐스팅하는 방식
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindNotification(Queue notificationQueue, FanoutExchange fanoutExchange) {
        // FanoutExcahnge와 위에서 정의한 notification Queue를 바인딩 -> 이걸 해줘야 당연히 원하는 큐에 메세지를 전송할 수 있음
        // FanoutExchange는 라우팅 키가 필요 없는 방법이기 때문에 별도의 라우팅 키 설정할 필요 X -> Publisher 확인
        return BindingBuilder.bind(notificationQueue).to(fanoutExchange);
    }
}
