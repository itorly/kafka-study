package com.dn.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    //  Receive messages or data by means of listening.
    @KafkaListener(topics = {"hello-topic"}, groupId = "hello-group")
    public void onEvent(String eventStr) {
        System.out.println("EventConsumer onEvent: " + eventStr);
    }
}
