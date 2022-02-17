package com.example.consumer.exceptionhandling.dlx;

import com.example.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPictureFanoutRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyPictureFanoutRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.mypicture.image"})
    public void getMessageFromPictureProducer(String msg) throws JsonProcessingException {
        var picture = objectMapper.readValue(msg, Picture.class);
        if (picture.getSize() > 9000) {
            throw new AmqpRejectAndDontRequeueException("Picture size more than 9000");
        }
        LOGGER.info("Consuming {}", msg);
    }
}
