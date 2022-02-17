package com.example.rabbitmqproducer.producer.header;

import com.example.rabbitmqproducer.entity.Furniture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FurnitureHeaderProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageFromFurnitureProducer(Furniture furniture) throws JsonProcessingException {
        var messageProperties = new MessageProperties();
        messageProperties.setHeader("color", furniture.getColor());
        messageProperties.setHeader("material", furniture.getMaterial());

        var value = objectMapper.writeValueAsString(furniture);

        var message = new Message(value.getBytes(), messageProperties);

        rabbitTemplate.send("x.furniture", "", message);
    }
}
