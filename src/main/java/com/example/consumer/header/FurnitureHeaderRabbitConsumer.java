package com.example.consumer.header;

import com.example.entity.Furniture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class FurnitureHeaderRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FurnitureHeaderRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.furniture.discount", "q.furniture.free-delivery"})
    public void getMessageFromFurnitureProducer(Message msg) throws IOException {
        var body = new String(msg.getBody());
        LOGGER.info("Consuming msg {} with routing headers {}", body, msg.getMessageProperties().getHeaders());
        objectMapper.readValue(body, Furniture.class);
    }
}
