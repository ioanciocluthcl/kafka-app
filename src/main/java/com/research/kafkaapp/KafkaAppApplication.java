package com.research.kafkaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableKafkaStreams
@SpringBootApplication
public class KafkaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAppApplication.class, args);
    }

}
