package com.example;

import com.example.entity.Picture;
import com.example.entity.Source;
import com.example.entity.Type;
import com.example.producer.EmployeeRabbitProducer;
import com.example.producer.FixedRateRabbitProducer;
import com.example.producer.HelloRabbitProducer;
import com.example.producer.direct.PictureDirectProducer;
import com.example.producer.exceptionhandling.dlx.MyPictureFanoutProducer;
import com.example.producer.fanout.AccountingRabbitProducer;
import com.example.producer.header.FurnitureHeaderProducer;
import com.example.producer.retrymechanism.direct.PictureRetryDirectProducer;
import com.example.producer.topic.PictureTopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private PictureRetryDirectProducer pictureRetryDirectProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            var picture = new Picture("NAME" + i,
                    Type.getRandomType(),
                    Source.getRandomSource(),
                    ThreadLocalRandom.current().nextLong(9000, 10000)
            );
            System.out.println(picture);
            pictureRetryDirectProducer.sendMessageFromPictureProducer(picture);
        }
    }
}
