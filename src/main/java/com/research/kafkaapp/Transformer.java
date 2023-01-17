package com.research.kafkaapp;

import org.springframework.stereotype.Component;

@Component
public class Transformer {
    public MyMessage transform(MyMessage message) {
        message.setStatus("PROCESSED");
        return message;
    }
}
