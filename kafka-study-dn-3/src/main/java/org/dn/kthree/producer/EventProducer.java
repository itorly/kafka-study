package org.dn.kthree.producer;


import jakarta.annotation.Resource;
import org.dn.kthree.model.User;
import org.dn.kthree.util.JSONUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventProducer {

    //  Added the spring-kafka dependency and the corresponding .yml configuration information.
    //  With this, Spring Boot automatically configured Kafka and automatically wired the KafkaTemplate Bean.
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent() {
        for (int i = 0; i < 125; i++) {
            User user = User.builder().id(i).phone("1370909090"+i).birthDay(new Date()).build();
            String userJSON = JSONUtils.toJSON(user);
            kafkaTemplate.send("batchTopic", "k" + i, userJSON);
        }
    }
}
