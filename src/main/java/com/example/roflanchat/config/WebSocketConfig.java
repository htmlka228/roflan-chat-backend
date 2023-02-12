package com.example.roflanchat.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerAdapter;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig {
    private final WebSocketHandler webSocketHandler;

    @Bean
    public HandlerMapping handlerMapping() {
        String path = "/chat";
        Map<String, WebSocketHandler> map = Map.of(path, webSocketHandler);

        return new SimpleUrlHandlerMapping(map, -1);
    }

    @Bean
    public HandlerAdapter wsHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
