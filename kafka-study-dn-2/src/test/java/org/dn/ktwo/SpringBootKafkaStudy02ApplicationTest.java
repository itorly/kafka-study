package org.dn.ktwo;

import jakarta.annotation.Resource;
import org.dn.ktwo.model.User;
import org.dn.ktwo.producer.EventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SpringBootKafkaStudy02ApplicationTest {
    @Resource
    private EventProducer eventProducer;

    @Test
    public void testSendEvent() {
        eventProducer.sendEvent("helloTopic", "hello kafka");
    }

    @Test
    public void testSendEventWhenParameterIsBean() {
        User user = User.builder().
                id(1209).
                phone("13709090910").
                birthDay(new Date()).
                build();

        eventProducer.sendEventWhenParameterIsBean(user);
    }
}
