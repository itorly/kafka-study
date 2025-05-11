package org.dn.ktwo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    //  Send a message to heTopic.
    //  After restarting the project, the messages in the heTopic partition will not be lost.
    @Bean
    public NewTopic newTopic() {
        return new NewTopic("helloTopic", 5, (short) 1);
    }

}
