package com.dn.producer;

import com.dn.model.User;
import jakarta.annotation.Resource;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
public class EventProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate2;

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate3;

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

    public void sendDefaultThenBlockingToGet() {
        //Integer partition, Long timestamp, K key, V data
        CompletableFuture<SendResult<String, String>> completableFuture
                = kafkaTemplate.sendDefault(0, System.currentTimeMillis(), "k3", "hello kafka");

        try {
            //  1、Blocking to get the result.
            SendResult<String, String> sendResult = completableFuture.get();
            if (sendResult.getRecordMetadata() != null) {
                //  The Kafka server confirms that it has received the message.
                System.out.println("The message was sent successfully: " + sendResult.getRecordMetadata().toString());
            }
            System.out.println("producerRecord: " + sendResult.getProducerRecord());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendDefaultThenNonBlockingToGet() {
        //Integer partition, Long timestamp, K key, V data
        CompletableFuture<SendResult<String, String>> completableFuture
                = kafkaTemplate.sendDefault(0, System.currentTimeMillis(), "k3", "hello kafka");

        try {
            //2、Non-Blocking
            completableFuture.thenAccept((sendResult) -> {
                if (sendResult.getRecordMetadata() != null) {
                    //kafka服务器确认已经接收到了消息
                    System.out.println("The message was sent successfully: " + sendResult.getRecordMetadata().toString());
                }
                System.out.println("producerRecord: " + sendResult.getProducerRecord());
            }).exceptionally((t) -> {
                t.printStackTrace();
                //  Handle as a failure
                return null;
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEventWhenDataIsObject() {
        User user = User.builder().id(1208).phone("13709090909").birthDay(new Date()).build();
        //  The partition is null. Let Kafka decide by itself which partition to send the message to.
        kafkaTemplate3.sendDefault(null, System.currentTimeMillis(), "k3", user);
    }

    public void sendForTesting () {
        User user = User.builder().id(1208).phone("13709090909").birthDay(new Date()).build();
        kafkaTemplate2.send("heTopic", null, System.currentTimeMillis(), "k9", user);
    }
}
