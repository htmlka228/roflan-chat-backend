package com.example.roflanchat.service;

public interface ObjectMapperService {
    String writeValueAsString(Object source);
    <T> T readValue(String payload, Class<T> targetClass);
}
