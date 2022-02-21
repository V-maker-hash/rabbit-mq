package com.example.consumer.springmechanism;

import com.example.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class RetrySpringPictureConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetrySpringPictureConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);
        LOGGER.info("Consuming image {}", picture);

        if (picture.getSize() > 9000) {
            throw new IllegalArgumentException("Picture is too large!");
        }
        LOGGER.info("Processing image {}", picture);
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String message) throws JsonProcessingException {
        var vector = objectMapper.readValue(message, Picture.class);
        LOGGER.info("Consuming vector {}", vector);

        LOGGER.info("Processing vector {}", vector);
    }
}
