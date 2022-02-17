package com.example.rabbitmqconsumer.consumer.fanout;

import com.example.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketingRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.hr.marketing")
    private void getMessageFromMarketingProducer(String msg) throws JsonProcessingException {
        var employee = objectMapper.readValue(msg, Employee.class);
        LOGGER.info("Consuming in marketing {}", employee);
    }
}
