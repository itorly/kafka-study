package org.dn.ktwo.producer;

import jakarta.annotation.Resource;
import org.dn.ktwo.model.User;
import org.dn.ktwo.util.JSONUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate2;

    public void sendEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendEventWhenParameterIsBean(User user) {
        String userJSON = JSONUtils.toJSON(user);
        kafkaTemplate.send("helloTopic", userJSON);
    }

}
