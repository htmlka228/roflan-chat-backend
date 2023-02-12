package com.example.roflanchat.repository;

import com.example.roflanchat.document.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessageRepository extends ReactiveMongoRepository<Message, ObjectId> {
}
