//package com.sullung2yo.rabbitmq.controller;
//
//import com.sullung2yo.rabbitmq.distributed_consumers.Producer;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/distributed-consumer")
//public class DistributedConsumerController {
//
//    private final Producer producer;
//
//    public DistributedConsumerController(Producer producer) {
//        this.producer = producer;
//    }
//
//    @PostMapping("/queue")
//    public String workQueue(@RequestParam String message, @RequestParam int duration) {
//        for (int i = 0; i < 10; i++) {
//            producer.sendWorkToQueue(message, duration);
//        }
//        return "Successfully sent 10 messages";
//    }
//}
