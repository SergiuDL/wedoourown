package com.tzacapaca.wedoourown.service;

import com.tzacapaca.wedoourown.domain.Message;
import com.tzacapaca.wedoourown.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Flux<Message> findAll() {
        return messageRepository.findAll();
    }

    public Flux<Message> getAllByUserName(String userName) {
        return messageRepository.findAllByUsername(userName);
    }

    public Mono<Void> removeLastMessage(String username) {
        Message latestPostByUser = messageRepository.findLatestPostByUser(username);
        return messageRepository.delete(latestPostByUser);
    }
}
