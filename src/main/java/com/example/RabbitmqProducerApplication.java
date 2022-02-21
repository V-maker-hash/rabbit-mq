package com.example;

import com.example.entity.Picture;
import com.example.entity.Source;
import com.example.entity.Type;
import com.example.producer.springmechanism.PictureSpringRetryDirectProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private PictureSpringRetryDirectProducer pictureSpringRetryDirectProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            var picture = new Picture("" + i,
                    Type.getRandomType(),
                    Source.getRandomSource(),
                    ThreadLocalRandom.current().nextLong(9000, 10000));
            pictureSpringRetryDirectProducer.sendMessageFromPictureProducer(picture);
        }
    }
}
