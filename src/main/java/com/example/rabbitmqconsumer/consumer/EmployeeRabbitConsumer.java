package com.example.rabbitmqconsumer.consumer;

import com.example.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRabbitConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "course.employee")
    public void listenToEmployeeProducer(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOGGER.info("Consuming {}", employee);
    }
}
