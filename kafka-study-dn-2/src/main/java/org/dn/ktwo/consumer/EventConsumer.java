package org.dn.ktwo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    /**
     * Receive messages or data by means of listening.
     * @Payload: Mark this parameter as the content of the message body.
     * @Header: Mark this parameter as the content of the message header.
     * @param event
     * @param topic
     * @param partition
     */
    @KafkaListener(topics = {"helloTopic"}, groupId = "helloGroup")
    public void onEvent(
            @Payload String event,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
//            @Header(value = KafkaHeaders.RECEIVED_KEY) String key,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition) {
        System.out.println("EventConsumer onEvent: " + event +
                ", topic : " + topic +
                ", partition : " + partition);
    }
}
