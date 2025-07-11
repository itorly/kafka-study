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

    @Test
    public void testSendEventByProducerRecord() {
        String topicName = "test-topic";
        eventProducer.sendEventByProducerRecord(topicName, "hello kafka");
    }

    @Test
    public void testSend() {
        eventProducer.send();
    }

    @Test
    public void sendDefault() {
        eventProducer.sendDefault();
    }

    @Test
    public void sendDefaultThenBlockingToGet() {
        eventProducer.sendDefaultThenBlockingToGet();
    }

    @Test
    public void sendDefaultThenNonBlockingToGet() {
        eventProducer.sendDefaultThenNonBlockingToGet();
    }

    @Test
    public void sendEventWhenDataIsObject() {
        eventProducer.sendEventWhenDataIsObject();
    }

    @Test
    public void sendForTesting() {
        eventProducer.sendForTesting();
    }

    @Test
    public void sendWithoutPartitionParameter() {
        for (int i = 0; i < 10; i++) {
            eventProducer.sendWithoutPartitionParameter();
        }
    }

}
