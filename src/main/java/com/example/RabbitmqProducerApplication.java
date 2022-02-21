package com.example;

import com.example.entity.Employee;
import com.example.producer.springmechanism.EmployeeSpringRetryFanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private EmployeeSpringRetryFanoutProducer employeeSpringRetryFanoutProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 1; i++) {
            var picture = new Employee("" + i,
                    "",
                    LocalDate.now());
            employeeSpringRetryFanoutProducer.sendEmployeeInfo(picture);
        }
    }
}
