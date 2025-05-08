package com.dn.config;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CustomerProducerInterceptor implements ProducerInterceptor<String, Object> {

    /**
     * Called before record is sent to Kafka.
     * @param record The record that is about to be sent.
     * @return The record that will be actually sent.
     */
    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
        System.out.println("Intercepted messages: " + record.toString());
        return record;
    }

    /**
     * Called when the record sent to Kafka has been acknowledged by the broker.
     * @param metadata The metadata for the record that was sent (i.e. the partition and offset).
     *                 If an error occurred, metadata will contain only valid topic and maybe
     *                 partition. If partition is not given in ProducerRecord and an error occurs
     *                 before partition gets assigned, then partition will be set to RecordMetadata.NO_PARTITION.
     *                 The metadata may be null if the client passed null record to
     *                 {@link org.apache.kafka.clients.producer.KafkaProducer#send(ProducerRecord)}.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if ( metadata == null ) {
            System.out.println("Message was not sent. Exception Message: " + exception.getMessage());
        } else {
            System.out.println("Message was sent. Offset: " + metadata.offset());
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
