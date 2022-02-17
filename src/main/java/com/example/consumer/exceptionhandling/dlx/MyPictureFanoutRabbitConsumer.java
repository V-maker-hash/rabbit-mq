package com.example.consumer.exceptionhandling.dlx;

import com.example.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyPictureFanoutRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyPictureFanoutRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.mypicture.image"})
    public void getMessageFromPictureProducer(String msg, Channel channel,
                                              @Header(AmqpHeaders.DELIVERY_MODE) long tag) throws IOException {
        var picture = objectMapper.readValue(msg, Picture.class);
        if (picture.getSize() > 9000) {
//            throw new AmqpRejectAndDontRequeueException("Picture size more than 9000");
            channel.basicReject(tag, false);
            return;
        }
        LOGGER.info("Consuming {}", msg);
        channel.basicAck(tag, false);
    }
}
