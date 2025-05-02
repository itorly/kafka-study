package com.dn.producer;

import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
