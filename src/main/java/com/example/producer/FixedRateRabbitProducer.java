package com.example.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

//@Service
public class FixedRateRabbitProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(FixedRateRabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Scheduled(fixedRate = 500)
    public void sendRate() {
        var increment = atomicInteger.getAndIncrement();
        LOGGER.info("Increment = {}", increment);
        rabbitTemplate.convertAndSend("q.cloud.fixedRate", "Rate is " + increment);
    }
}
