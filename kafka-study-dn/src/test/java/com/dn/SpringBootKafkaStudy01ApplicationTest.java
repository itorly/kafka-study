package com.dn;

import com.dn.producer.EventProducer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootKafkaStudy01ApplicationTest {

    @Resource
    private EventProducer eventProducer;

    @Test
    public void testSendEvent() {
        eventProducer.sendEvent("hello-topic", "hello kafka");
    }

    @Test
    public void testSendEventByMessage() {
        String topicName = "test-topic";
        eventProducer.sendEventByMessage(topicName, "hello");
    }

}
