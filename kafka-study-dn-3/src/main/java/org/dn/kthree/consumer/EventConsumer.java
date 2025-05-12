package org.dn.kthree.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventConsumer {

    @KafkaListener(topics = {"batchTopic"}, groupId = "batchGroup2")
    //  List type argument
    public void onEvent(List<ConsumerRecord<String, String>> records) {
        System.out.println(
            "Batch Consumption: \n" +
                "records.size() = " + records.size() + ",\n" +
                    "records = " + records
        );
    }
}
