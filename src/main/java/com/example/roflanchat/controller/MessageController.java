package com.example.roflanchat.controller;

import com.example.roflanchat.document.Message;
import com.example.roflanchat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @PostMapping("/messages")
    public Mono<Message> generateMessage() {
        Message message = Message.builder()
                .text(randomString(10))
                .sentTime(LocalDateTime.now())
                .build();

        return messageRepository.save(message);
    }

    @GetMapping("/messages")
    public Flux<Message> getMessages() {
        return messageRepository.findAll();
    }

    private String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        return sb.toString();
    }
}
