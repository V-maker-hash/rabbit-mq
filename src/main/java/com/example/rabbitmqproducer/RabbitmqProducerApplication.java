package com.example.rabbitmqproducer;

import com.example.rabbitmqproducer.entity.Furniture;
import com.example.rabbitmqproducer.producer.EmployeeRabbitProducer;
import com.example.rabbitmqproducer.producer.FixedRateRabbitProducer;
import com.example.rabbitmqproducer.producer.HelloRabbitProducer;
import com.example.rabbitmqproducer.producer.direct.PictureDirectProducer;
import com.example.rabbitmqproducer.producer.fanout.AccountingRabbitProducer;
import com.example.rabbitmqproducer.producer.header.FurnitureHeaderProducer;
import com.example.rabbitmqproducer.producer.topic.PictureTopicProducer;
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

    private static final List<String> COLORS = List.of("red", "black");
    private static final List<String> MATERIALS = List.of("wood", "steel");
    private static final Random RANDOM = new Random();

    @Autowired
    private HelloRabbitProducer producer;

    @Autowired
    private FixedRateRabbitProducer fixedRateRabbitProducer;

    @Autowired
    private EmployeeRabbitProducer employeeRabbitProducer;

    @Autowired
    private AccountingRabbitProducer accountingRabbitProducer;

    @Autowired
    private PictureDirectProducer pictureDirectProducer;

    @Autowired
    private PictureTopicProducer pictureTopicProducer;

    @Autowired
    private FurnitureHeaderProducer furnitureHeaderProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        producer.sendHello("VASILIY");
//        fixedRateRabbitProducer.sendRate();
        for (int i = 0; i < 5; i++) {
//            var emp = new Employee("" + i, "Employee" + i, LocalDate.now());
//            employeeRabbitProducer.sendEmployeeInfo(emp);
//            accountingRabbitProducer.sendAccountingInfo(emp);

//            var picture = new Picture("NAME" + i,
//                    Type.getRandomType(),
//                    Source.getRandomSource(),
//                    ThreadLocalRandom.current().nextLong(1, 10000)
//            );
//            System.out.println(picture);
//            pictureDirectProducer.sendMessageFromPictureProducer(picture);
//            pictureTopicProducer.sendMessageFromPictureProducer(picture);

            var furniture = new Furniture(getRandomColor(), getRandomMaterial(), "Furniture" + i,
                    ThreadLocalRandom.current().nextInt(100, 600));
            System.out.println(furniture);
            furnitureHeaderProducer.sendMessageFromFurnitureProducer(furniture);

        }

    }

    private String getRandomMaterial() {
        return MATERIALS.get(RANDOM.nextInt(MATERIALS.size()));
    }

    private String getRandomColor() {
        return COLORS.get(RANDOM.nextInt(COLORS.size()));
    }
}
