//package com.research.kafkaapp;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.kstream.Consumed;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.Produced;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Processor {
//
//    @Autowired
//    protected Transformer transformer;
//
//    @Autowired
//    public void process(StreamsBuilder builder) {
//        KStream<String, MyMessage> myTopic = builder.stream("input-topic", Consumed.with(Serdes.String(),
//                Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(MyMessage.class))));
//        myTopic.mapValues(transformer::transform).to("output-topic", Produced.with(Serdes.String(),
//                Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(MyMessage.class))));
//        Topology topology = builder.build();
//        System.out.println(topology.describe());
//    }
//}
