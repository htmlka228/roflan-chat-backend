package com.example.roflanchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class RoflanChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoflanChatApplication.class, args);
    }

}
