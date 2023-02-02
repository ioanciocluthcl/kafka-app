package com.research.kafkaapp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j
@Data
public class KafkaTopicConfiguration {

    @Value("${kafka.input.topic}")
    private String inputTopic;

    @Value("${kafka.output.topic}")
    private String outputTopic;

    @Bean
    public NewTopic inputTopic() {
        NewTopic topic = TopicBuilder.name(this.inputTopic).partitions(5).replicas(1).build();
        log.info("Created topic: {}", topic);
        return topic;
    }

    @Bean
    public NewTopic outputTopic() {
        NewTopic topic = TopicBuilder.name(this.outputTopic).partitions(5).replicas(1).build();
        log.info("Created topic: {}", topic);
        return topic;
    }
}