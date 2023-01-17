package com.research.kafkaapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/messages")
    public void sendMessage(@RequestBody MyMessage myMessage) throws JsonProcessingException {
        System.out.println("Send: " + myMessage);
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(myMessage);
        kafkaTemplate.send("input-topic", value);
    }

    @KafkaListener(topics = "output-topic", groupId = "my-group20")
    public void consume(MyMessage message) {
        System.out.println("Received on output-topic: " + message);
    }

}
