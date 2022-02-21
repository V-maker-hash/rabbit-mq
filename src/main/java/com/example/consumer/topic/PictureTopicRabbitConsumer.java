package com.example.consumer.topic;

import com.example.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class PictureTopicRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureTopicRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.picture2.image", "q.picture2.vector", "q.picture2.log", "q.picture2.filter"})
    public void getMessageFromPictureProducer(Message msg) throws IOException {
        var body = new String(msg.getBody());
        LOGGER.info("Consuming msg {} with routing key {}", body, msg.getMessageProperties().getReceivedRoutingKey());
        objectMapper.readValue(body, Picture.class);
    }
}
