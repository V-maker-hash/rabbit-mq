package com.example.consumer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RabbitmqScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqScheduler.class);

    @Autowired
    private RabbitListenerEndpointRegistry registry;

    @Scheduled(cron = "0 48 15 * * *")
    public void stopAll(){
        registry.getListenerContainers().forEach(messageListenerContainer -> {
            LOGGER.info("Stopping listener container {}", messageListenerContainer);
            messageListenerContainer.stop();
        });
    }

    @Scheduled(cron = "0 49 15 * * *")
    public void startAll(){
        registry.getListenerContainers().forEach(messageListenerContainer -> {
            LOGGER.info("Starting listener container {}", messageListenerContainer);
            messageListenerContainer.start();
        });
    }
}
