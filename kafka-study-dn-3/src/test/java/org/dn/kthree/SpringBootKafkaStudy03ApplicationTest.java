package org.dn.kthree;

import jakarta.annotation.Resource;
import org.dn.kthree.producer.EventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootKafkaStudy03ApplicationTest {

    @Resource
    private EventProducer eventProducer;

    @Test
    void test01() {
        eventProducer.sendEvent();
    }

}
