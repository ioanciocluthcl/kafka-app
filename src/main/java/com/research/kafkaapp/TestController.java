package com.research.kafkaapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    protected KafkaTopicConfiguration topicConfiguration;

    private int counter = 1000;

    @PostMapping("/messages")
    @Transactional
    public void sendMessage(@RequestBody MyMessage myMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(myMessage);
        String key = "key" + counter;
        counter++;
        log.info("Sending on topic {}: {}", topicConfiguration.getInputTopic(), value);
        kafkaTemplate.send(topicConfiguration.getInputTopic(), key, value);
    }

    @Transactional
    @KafkaListener(topics = "#{'${kafka.output.topic}'.split(',')}", groupId = "my-group22")
    public void consume(String message) {
        log.info("Received on {}: {}", topicConfiguration.getOutputTopic(), message);
    }

}
