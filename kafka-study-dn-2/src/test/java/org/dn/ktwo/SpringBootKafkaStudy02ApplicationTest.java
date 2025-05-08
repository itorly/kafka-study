package org.dn.ktwo;

import jakarta.annotation.Resource;
import org.dn.ktwo.producer.EventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootKafkaStudy02ApplicationTest {
    @Resource
    private EventProducer eventProducer;

    @Test
    public void testSendEvent() {
        eventProducer.sendEvent("helloTopic", "hello kafka");
    }
}
