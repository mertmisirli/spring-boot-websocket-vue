package com.yurticikargo.gib.repository;

import com.yurticikargo.gib.repository.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<ChatMessage, Long> {

}
