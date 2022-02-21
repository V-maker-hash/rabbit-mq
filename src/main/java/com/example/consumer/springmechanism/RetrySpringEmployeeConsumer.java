package com.example.consumer.springmechanism;

import com.example.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetrySpringEmployeeConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RetrySpringEmployeeConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;


    @RabbitListener(queues = "q.spring2.accounting.work")
    public void listenToAccounting(String message) throws IOException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOG.info("On ACCOUNTING : {}", employee);
        if (StringUtils.isEmpty(employee.getName())) {
            throw new IllegalArgumentException("Name is empty");
        }
    }

    @RabbitListener(queues = "q.spring2.marketing.work")
    public void listenToMarketing(String message) throws IOException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOG.info("On MARKETING : {}", employee);
    }
}
