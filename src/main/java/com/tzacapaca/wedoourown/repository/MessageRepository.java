package com.tzacapaca.wedoourown.repository;

import com.tzacapaca.wedoourown.domain.Message;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Reactive mongo repository for messages.
 * @author selascu
 */
@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
