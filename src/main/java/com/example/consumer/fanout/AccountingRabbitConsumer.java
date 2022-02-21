package com.example.consumer.fanout;

import com.example.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class AccountingRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.hr.accounting")
    private void getMessageFromAccountingProducer(String msg) throws JsonProcessingException {
        var employee = objectMapper.readValue(msg, Employee.class);
        LOGGER.info("Consuming in accounting {}", employee);
    }
}
