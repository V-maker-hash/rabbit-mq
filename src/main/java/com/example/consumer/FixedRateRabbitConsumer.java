package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class FixedRateRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateRabbitConsumer.class);

    @RabbitListener(queues = "q.cloud.fixedRate", concurrency = "3-7")
    public void listenToFixedRateProducer(String message) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1000, 2000));
        LOGGER.info("{}: Consuming {}", Thread.currentThread().getName(), message);
    }
}
