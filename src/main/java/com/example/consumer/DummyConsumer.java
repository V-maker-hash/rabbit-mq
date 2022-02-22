package com.example.consumer;

import com.example.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class DummyConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitListener(queues = "q.dummy")
    public void listenDummy(DummyMessage dummyMessage) {

        LOGGER.info("Consuming dummy: {}", dummyMessage);
    }
}
