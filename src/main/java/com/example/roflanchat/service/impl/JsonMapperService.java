package com.example.roflanchat.service.impl;

import com.example.roflanchat.service.ObjectMapperService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonMapperService implements ObjectMapperService {
    private final JsonMapper jsonMapper;

    @Override
    @SneakyThrows
    public String writeValueAsString(Object source) {
        return jsonMapper.writeValueAsString(source);
    }

    @Override
    @SneakyThrows
    public <T> T readValue(String payload, Class<T> targetClass) {
        return jsonMapper.readValue(payload, targetClass);
    }
}
