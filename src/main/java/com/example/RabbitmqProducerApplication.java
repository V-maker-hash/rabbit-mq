package com.example;

import com.example.entity.Employee;
import com.example.producer.retrymechanism.fanout.RetryEmployeeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private RetryEmployeeProducer retryEmployeeProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            var employee = new Employee("" + i, null, LocalDate.now());
            System.out.println(employee);
            retryEmployeeProducer.sendEmployeeInfo(employee);
        }
    }
}
