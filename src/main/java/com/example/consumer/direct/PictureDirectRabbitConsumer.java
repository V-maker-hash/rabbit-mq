package com.example.consumer.direct;

import com.example.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureDirectRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureDirectRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

//    @RabbitListener(queues = {"q.picture.image", "q.picture.vector"})
    public void getMessageFromPictureProducer(String msg) throws JsonProcessingException {
        LOGGER.info("Consuming {}", msg);
        objectMapper.readValue(msg, Picture.class);
    }
}
