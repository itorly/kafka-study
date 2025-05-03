package com.dn.producer;

import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendEventByMessage(String topicName, String string) {
        Message<String> stringMessage =
                MessageBuilder.withPayload(string)
                        .setHeader(KafkaHeaders.TOPIC, topicName)
                        .build();
        kafkaTemplate.send(stringMessage);
    }
}
