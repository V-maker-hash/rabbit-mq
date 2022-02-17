package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HelloRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloRabbitConsumer.class);

    @RabbitListener(queues = "course.hello")
    public void listenToHelloProducer(String message) {
        LOGGER.info("Consuming {}", message);
    }
}
