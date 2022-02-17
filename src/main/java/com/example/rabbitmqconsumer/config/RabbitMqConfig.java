package com.example.rabbitmqconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return JsonMapper.builder().findAndAddModules().build();
    }
}
