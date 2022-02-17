package com.example.rabbitmqproducer.producer.fanout;

import com.example.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingRabbitProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAccountingInfo(Employee employee) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("x.hr", "", objectMapper.writeValueAsString(employee));
    }
}
