package org.dn.ktwo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    //  Send a message to heTopic.
    //  After restarting the project, the messages in the heTopic partition will not be lost.
    @Bean
    public NewTopic newTopic() {
        return new NewTopic("heTopic", 5, (short) 1);
    }

}
