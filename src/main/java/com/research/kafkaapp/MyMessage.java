package com.research.kafkaapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyMessage {
    private String transactionId;
    private String transactionStatus;
    private List<String> transactionTracking = new ArrayList<>();
    private String transactionTimestamp;
}
