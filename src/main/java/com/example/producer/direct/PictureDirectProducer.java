package com.example.producer.direct;

import com.example.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureDirectProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageFromPictureProducer(Picture picture) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(picture);
        rabbitTemplate.convertAndSend("x.picture", picture.getType(), value);
    }
}
