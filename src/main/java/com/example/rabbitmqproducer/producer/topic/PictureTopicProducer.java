package com.example.rabbitmqproducer.producer.topic;

import com.example.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureTopicProducer {

    private static final String LARGE = "large";
    private static final String SMALL = "small";
    private static final String DELIMITER = ".";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageFromPictureProducer(Picture picture) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(picture);
        String routingKey = picture.getSource() +
                DELIMITER +
                getSizeType(picture.getSize()) +
                DELIMITER +
                picture.getType();
        System.out.println(routingKey);
        rabbitTemplate.convertAndSend("x.picture2", routingKey, value);
    }

    private String getSizeType(long size) {
        if (size > 2000) return LARGE;
        else return SMALL;
    }
}
