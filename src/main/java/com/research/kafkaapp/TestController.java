package com.research.kafkaapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 1000;

    @PostMapping("/messages")
    @Transactional
    public void sendMessage(@RequestBody MyMessage myMessage) throws JsonProcessingException {
        System.out.println("Send: " + myMessage);
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(myMessage);
        String key = "key" + counter;
        counter++;
        kafkaTemplate.send("transform", key, value);
    }

    @Transactional
    @KafkaListener(topics = "messageprocessed", groupId = "my-group22")
    public void consume(String message) {
        System.out.println("Received on output-topic: " + message);
    }

}
