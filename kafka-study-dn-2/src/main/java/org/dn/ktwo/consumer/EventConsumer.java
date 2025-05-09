package org.dn.ktwo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dn.ktwo.model.User;
import org.dn.ktwo.util.JSONUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer<T> {

    /**
     * Receive messages or data by means of listening.
     * @Payload: Mark this parameter as the content of the message body.
     * @Header: Mark this parameter as the content of the message header.
     * @param event
     * @param topic
     * @param partition
     */
//    @KafkaListener(topics = {"helloTopic"}, groupId = "helloGroup")
    public void onEvent(
            @Payload String event,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
//            @Header(value = KafkaHeaders.RECEIVED_KEY) String key,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
            ConsumerRecord<String, String> consumerRecord) {
        System.out.println("EventConsumer onEvent 1: " + event +
                ", topic : " + topic +
                ", partition : " + partition);
        System.out.println("consumerRecord : " + consumerRecord.toString());
    }

    @KafkaListener(topics = {"helloTopic"}, groupId = "helloGroup")
    public void onEventWhenParameterIsJsonString(
            String jsonString,
//            Class<T> clazz,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
            ConsumerRecord<String, String> consumerRecord) {
//        T t = JSONUtils.toBean(jsonString, clazz);
        User user = JSONUtils.toBean(jsonString, User.class);
        System.out.println("EventConsumer onEvent 1: " + jsonString +
                ", \nuser : " + user +
                ", \ntopic : " + topic +
                ", \npartition : " + partition);
        System.out.println("consumerRecord : " + consumerRecord.toString());
    }
}
