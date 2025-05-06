package com.dn.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

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
