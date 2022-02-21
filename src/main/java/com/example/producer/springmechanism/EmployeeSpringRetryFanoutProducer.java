package com.example.producer.springmechanism;

import com.example.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSpringRetryFanoutProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmployeeInfo(Employee employee) throws JsonProcessingException {
        var value = objectMapper.writeValueAsString(employee);
        rabbitTemplate.convertAndSend("x.spring2.work", "", value);
    }
}
