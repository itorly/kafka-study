package org.dn.ktwo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dn.ktwo.model.User;
import org.dn.ktwo.util.JSONUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
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

//    @KafkaListener(topics = {"helloTopic"}, groupId = "helloGroup")
    public void onEventWhenParameterIsJsonString(
            String jsonString,
//            Class<T> clazz,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
            ConsumerRecord<String, String> consumerRecord) {
//        T t = JSONUtils.toBean(jsonString, clazz);
        User user = JSONUtils.toBean(jsonString, User.class);
        System.out.println("EventConsumer onEvent 2: " + jsonString +
                ", \nuser : " + user +
                ", \ntopic : " + topic +
                ", \npartition : " + partition);
        System.out.println("consumerRecord : " + consumerRecord.toString());
    }

//    @KafkaListener(topics = {"${kafka.topic.name}"}, groupId = "${kafka.consumer.group}")
    public void onEvent3(
            String jsonString,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
            @Payload ConsumerRecord<String, String> consumerRecord) {
        User user = JSONUtils.toBean(jsonString, User.class);
        System.out.println("EventConsumer onEvent 3: " + jsonString +
                ", \nuser : " + user +
                ", \ntopic : " + topic +
                ", \npartition : " + partition);
        System.out.println("consumerRecord : " + consumerRecord.toString());
    }

//    @KafkaListener(topics = {"${kafka.topic.name}"}, groupId = "${kafka.consumer.group}")
    public void onEvent4(
            String jsonString,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
            @Payload ConsumerRecord<String, String> consumerRecord,
            Acknowledgment ack) {
        try {
            User user = JSONUtils.toBean(jsonString, User.class);
            System.out.println("EventConsumer onEvent 4: " + jsonString +
                    ", \nuser : " + user +
                    ", \ntopic : " + topic +
                    ", \npartition : " + partition);
            System.out.println("consumerRecord : " + consumerRecord.toString());
            //  Business processing is completed. Confirm to the Kafka server.
            ack.acknowledge();  //   Manually confirming a message means informing the Kafka server that this message have been received . By default, Kafka automatically confirms messages.
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(groupId = "${kafka.consumer.group}",
            topicPartitions = {
                    @TopicPartition(
                            topic = "${kafka.topic.name}",
                            partitions = {"1"},
                            partitionOffsets = {
                                    @PartitionOffset(partition = "0", initialOffset = "1"),
                                    @PartitionOffset(partition = "2", initialOffset = "2"),
                                    @PartitionOffset(partition = "3", initialOffset = "3"),
                                    @PartitionOffset(partition = "4", initialOffset = "3")
                            })
            })
    public void onEvent5(String userJSON,
                         @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(value = KafkaHeaders.RECEIVED_PARTITION) String partition,
                         @Payload ConsumerRecord<String, String> record,
                         Acknowledgment ack) {
        try {
            //  After receiving the message, process the business.
            User user = JSONUtils.toBean(userJSON, User.class);
            System.out.println("EventConsumer onEvent 5：" + user + ", topic : " + topic + ", partition : " + partition);
//            System.out.println("ConsumerRecord: " + record.toString());
            //  Business processing is completed. Confirm to the Kafka server.
            ack.acknowledge(); //   Manually confirming a message means informing the Kafka server that this message have been received . By default, Kafka automatically confirms messages.
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
