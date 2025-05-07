package com.dn.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {


    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    /**
     * Producer-related configuration
     */
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(6);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        return props;
     }


    //  Send a message to heTopic.
    //  After restarting the project, the messages in the heTopic partition will not be lost.
    @Bean
    public NewTopic newTopic() {
        return new NewTopic("heTopic", 5, (short) 1);
    }

    //  Modifying the number of partitions will not result in data loss.
    //  However, the number of partitions can only be increased but not decreased.
    @Bean
    public NewTopic updateNewTopic() {
        return new NewTopic("heTopic", 7, (short) 1);
    }
}
