package com.research.kafkaapp;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name("transform").partitions(5).replicas(1).build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("messageprocessed").partitions(5).replicas(1).build();
    }
}