package com.example.consumer.retrymechanism.fanout;

import com.example.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryMarketingConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RetryMarketingConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.guideline2.marketing.work")
    public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        var body = message.getBody();
        var employee = objectMapper.readValue(body, Employee.class);
        LOG.info("On marketing : {}", employee);
        channel.basicAck(tag, false);
    }
}