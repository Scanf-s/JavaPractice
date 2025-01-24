package com.sullung2yo.rabbitmq.controller;

import com.sullung2yo.rabbitmq.direct_exchange.Sender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final Sender sender;

    public MessageController(Sender sender) {
        this.sender = sender;
    }

    @PostMapping("")
    public String sendMessage(@RequestBody String message) {
        sender.send(message); // Produce message
        return "Successfully sent message : " + message;
    }

}
