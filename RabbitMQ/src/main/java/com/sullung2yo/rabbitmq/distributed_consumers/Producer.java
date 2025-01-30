//package com.sullung2yo.rabbitmq.distributed_consumers;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Producer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public Producer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void sendWorkToQueue(String workQueueMessage, int duration) {
//        String message = workQueueMessage + "|" + duration; // Hello|1234
//        rabbitTemplate.convertAndSend(DistributedConsumerConfig.QUEUE_NAME, message);
//        System.out.println("Sent work " + message);
//    }
//}
