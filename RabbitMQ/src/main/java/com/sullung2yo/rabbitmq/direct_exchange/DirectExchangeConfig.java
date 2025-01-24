package com.sullung2yo.rabbitmq.direct_exchange;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    // 메세지 큐 이름 설정
    public static final String QUEUE_NAME = "MyQueue";

    @Bean
    public Queue queue() {
        // amqp Queue
        // QUEUE_NAME : Queue 이름 지정
        // durable: false (휘발성-volatile), true (영속성-persistent)
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        /*
         * RabbitMQ와 통신하기 위한 Template instance
         * 뿐만 아니라, 메세지 송수신할때도 필요함
         * 메시지를 전송하는 Sender가 rabbitTemplate.convertAndSend() 메서드를 사용해 큐에 메시지를 넣는 데 사용
         */
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        /*
         * RabbitMQ 메세지를 비동기적으로 수신하는 SimpleMessageListenerContainer instance
         * 특정 큐를 지속적으로 모니터링하고,
         * 메시지를 수신하면 지정된 리스너(MessageListenerAdapter)를 통해 처리
         * ConnectionFactory : RabbitMQ와 지속적인 연결 관리
         */
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory); // 연결 관리 인스턴스 설정
        container.setQueueNames(QUEUE_NAME); // 메세지를 수신하려는 큐 이름 설정
        container.setMessageListener(messageListenerAdapter); // 리스너 인스턴스 설정 -> AMQP에서 메세지를 자동으로 수신하기 위해 필요
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
        /*
         * 이 Bean은 수신한 메세지를 특정 클래스의 특정 메서드로 전달할 때 사용함
         * Receiver는 메세지를 처리하는 역할을 수행 (Comsumer 역할) -> 직접 어떻게 메세지를 처리할지는 알아서 구현해야겠지??
         * RabbitMQ에서 수신된 메세지를 특정 메서드에 전달 -> Receiver의 receiveMessage 메서드가 호출되고 -> 메세지를 처리하게 되는 방식
         */
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
