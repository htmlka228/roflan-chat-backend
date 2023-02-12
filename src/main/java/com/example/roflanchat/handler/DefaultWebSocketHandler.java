package com.example.roflanchat.handler;

import com.example.roflanchat.document.Message;
import com.example.roflanchat.repository.MessageRepository;
import com.example.roflanchat.service.ObjectMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultWebSocketHandler implements WebSocketHandler {
    private final MessageRepository messageRepository;
    private final ObjectMapperService objectMapperService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> messages = session.receive()
                .map(message -> objectMapperService.readValue(message.getPayloadAsText(), Message.class))
                .doOnNext(this::setSentTimeIfEmpty)
                .flatMap(messageRepository::save)
                .mergeWith(messageRepository.findAll())
                .map(objectMapperService::writeValueAsString)
                .map(session::textMessage);

        return session.send(messages);
    }

    private void setSentTimeIfEmpty(Message message) {
        if (message.getSentTime() == null) {
            message.setSentTime(LocalDateTime.now());
        }
    }
}
