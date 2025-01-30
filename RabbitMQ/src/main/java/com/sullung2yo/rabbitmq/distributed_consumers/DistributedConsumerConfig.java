//package com.sullung2yo.rabbitmq.distributed_consumers;
//
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DistributedConsumerConfig {
//    // Queue name
//    public static final String QUEUE_NAME = "MyQueue";
//
//    @Bean
//    public Queue queue(){
//        return new Queue(QUEUE_NAME, true); // duration=True로 설정하여 실패하더라도 작업이 여전히 남아있도록 구성
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        /*
//         * RabbitMQ와 통신하기 위한 Template instance
//         * 뿐만 아니라, 메세지 송수신할때도 필요함
//         * 메시지를 전송하는 Sender가 rabbitTemplate.convertAndSend() 메서드를 사용해 큐에 메시지를 넣는 데 사용
//         */
//        return new RabbitTemplate(connectionFactory);
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
//        /*
//         * RabbitMQ 메세지를 비동기적으로 수신하는 SimpleMessageListenerContainer instance
//         * 특정 큐를 지속적으로 모니터링하고,
//         * 메시지를 수신하면 지정된 리스너(MessageListenerAdapter)를 통해 처리
//         * ConnectionFactory : RabbitMQ와 지속적인 연결 관리
//         */
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory); // 연결 관리 인스턴스 설정
//        container.setQueueNames(QUEUE_NAME); // 메세지를 수신하려는 큐 이름 설정
//        container.setMessageListener(messageListenerAdapter); // 리스너 인스턴스 설정 -> AMQP에서 메세지를 자동으로 수신하기 위해 필요
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//
//        // 동시에 처리할 수 있는 Consumer 개수
//        container.setConcurrentConsumers(3);
//
//        // Round Robin 방식으로 동작하기 위해, RabbitMQ가 각 Consumer에게 하나씩만 메세지를 보내도록 설정
//        container.setPrefetchCount(1);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListenerAdapter(Consumer consumer) {
//        return new MessageListenerAdapter(consumer, "consume");
//    }
//
//}
