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
    public void test01() {
        eventProducer.sendEvent("hello-topic", "hello kafka");
    }

}
