package com.tzacapaca.wedoourown.service;

import com.tzacapaca.wedoourown.domain.Message;
import com.tzacapaca.wedoourown.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for {@link Message}.
 * @author selascu
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Finds all {@link Message}s.
     * @return a {@link Flux} of all the messages.
     */
    public Flux<Message> findAll() {
        return messageRepository.findAll();
    }
    
    /**
     * Finds all {@link Message}s for the given user.
     * @param userName - the user name.
     * @return a {@link Flux} of all the messages for the given user.
     */
    public Flux<Message> getAllByUserName(String userName) {
        return messageRepository.findAllByUsername(userName);
    }

    /**
     * Removes the last {@link Message} for the given user.
     * @param username - the username.
     * @return a {@link Mono} with no information.
     */
    public Mono<Void> removeLastMessage(String username) {
        Message latestPostByUser = messageRepository.findLatestPostByUser(username);
        return messageRepository.delete(latestPostByUser);
    }
}
