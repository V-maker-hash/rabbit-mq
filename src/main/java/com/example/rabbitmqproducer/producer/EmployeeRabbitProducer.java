package com.example.rabbitmqproducer.producer;

import com.example.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRabbitProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmployeeInfo(Employee employee) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("course.employee", objectMapper.writeValueAsString(employee));
    }
}
