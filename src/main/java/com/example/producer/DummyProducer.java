package com.example.producer;

import com.example.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDummyMsg(DummyMessage dummyMessage) {
        rabbitTemplate.convertAndSend("x.dummy", "", dummyMessage);
    }
}
