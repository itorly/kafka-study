package org.dn.ktwo.producer;

import jakarta.annotation.Resource;
import org.dn.ktwo.model.User;
import org.dn.ktwo.util.JSONUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate2;

    public void sendEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendEventWhenParameterIsBean(User user, String topic) {
        String userJSON = JSONUtils.toJSON(user);
        kafkaTemplate.send(topic, userJSON);
    }

    public void sendEventWhenParameterIsBean(String topic, String keyPrefix, String phoneNumPrefix) {
        for ( int i = 0; i < 25; i++) {
            String formatted = String.format("%02d", i);
            User user = User.builder().
                    id(i).
                    phone(phoneNumPrefix + formatted).
                    birthDay(new Date()).
                    build();

            String key = keyPrefix + i;

            String userJSON = JSONUtils.toJSON(user);
            kafkaTemplate.send(topic, key, userJSON);
        }
    }

}
