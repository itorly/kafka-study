package com.dn.producer;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

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

    public void sendEventByProducerRecord(String topicName, String string) {
        Headers headers = new RecordHeaders();
        headers.add("k1", "v1".getBytes());
        headers.add("orderId", "1234567890".getBytes(StandardCharsets.UTF_8));

        //  String topic, Integer partition, Long timestamp, K key, V value, Iterable<Header> headers
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                topicName,
                0,
                System.currentTimeMillis(),
                "key1",
                string,
                headers
        );
        kafkaTemplate.send(producerRecord);
    }

    public void send() {
        //String topic, Integer partition, Long timestamp, K key, V data
        kafkaTemplate.send("test-topic", 0, System.currentTimeMillis(), "k2", "hello kafka");
    }

    public void sendDefault() {
        //Integer partition, Long timestamp, K key, V data
        kafkaTemplate.sendDefault(0, System.currentTimeMillis(), "k3", "hello kafka");
    }
}
