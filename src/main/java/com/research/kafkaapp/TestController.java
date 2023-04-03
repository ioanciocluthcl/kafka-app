package com.research.kafkaapp;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private long start;

    @PostMapping("/messages")
    @Transactional
    public void sendMessage(@RequestBody String message) throws JsonProcessingException {
        String key = "key" + counter;
        counter++;
        log.info("Sending on topic {}: {}", topicConfiguration.getInputTopic(), message);
        start = System.currentTimeMillis();
        kafkaTemplate.send(topicConfiguration.getInputTopic(), key, message);
    }

    @Transactional
    @KafkaListener(topics = "#{'${kafka.output.topic}'.split(',')}", groupId = "my-group22")
    public void consume(String message) {
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        log.info("Received on {}: {}", topicConfiguration.getOutputTopic(), message);
    }

}
