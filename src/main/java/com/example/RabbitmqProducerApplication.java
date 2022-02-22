package com.example;

import com.example.entity.DummyMessage;
import com.example.producer.DummyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private DummyProducer dummyProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (var i = 0; i < 10_000; i++) {
            var dummyMessage = new DummyMessage("Now is " + LocalDate.now(), i);
            dummyProducer.sendDummyMsg(dummyMessage);
            TimeUnit.SECONDS.sleep(1);
        }


    }
}
