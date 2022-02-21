package com.example.scheduler;

import com.example.client.RabbitmqClient;
import com.example.entity.RabbitmqQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//@Service
public class RabbitMqScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqScheduler.class);

    @Autowired
    private RabbitmqClient rabbitmqClient;

    @Scheduled(fixedDelay = 90000)
    public void sweepDirtyQueues() {
        try {
            var dirtyQueues = rabbitmqClient.getAllQueues().stream().filter(RabbitmqQueue::isDirty).collect(Collectors.toList());
            dirtyQueues.forEach(queue -> LOGGER.info("Queue {} has {} unprocessed messages", queue.getName(), queue.getMessages()));
        } catch (Exception e) {
            LOGGER.warn("Cannot sweep queue {}", e.getMessage());
        }
    }
}
