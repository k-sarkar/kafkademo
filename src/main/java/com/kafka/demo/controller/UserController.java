package com.kafka.demo.controller;

import com.github.javafaker.Faker;
import com.kafka.demo.constant.DemoConstant;
import com.kafka.demo.pojo.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private KafkaTemplate<String, UserMessage> template;

    @PostMapping("/users")
    public String publishUser() {

        Faker faker = Faker.instance();
        UserMessage message = UserMessage.builder()
                .userId(UUID.randomUUID().toString())
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .build();

        logger.info("publishing user {}", message);

        template.send(DemoConstant.USER_TOPIC_NAME, message);

        return message.getUserId();
    }
}
