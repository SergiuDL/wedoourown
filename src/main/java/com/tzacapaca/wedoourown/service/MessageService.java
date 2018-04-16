package com.tzacapaca.wedoourown.service;

import com.tzacapaca.wedoourown.domain.Message;
import com.tzacapaca.wedoourown.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for {@link Message}.
 * @author selascu
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private Map<String, String> keywords;

    public MessageService() {
        this.keywords = new HashMap<>();
        keywords.put("hello", "Is it me you are looking for?");
        keywords.put("dark", "come with me to the dark side");
    }

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

    /**
     * Inserts the given {@link Message}.
     * @param message - the message to insert.
     * @return a {@link Mono} with the saved message or the response from the BOT.
     */
    public Mono<Message> save(Message message) {
        messageRepository.insert(message);
        Message response = checkMessageForBOT(message);
        return Mono.just(response);
    }

    private Message checkMessageForBOT(Message message) {
        List<String> identifiedkeywords = keywords.keySet().stream()
                .filter(keyword -> message.getBody().contains(keyword))
                .collect(Collectors.toList());
        if (identifiedkeywords.isEmpty()) {
            return message;
        }
        Message response = new Message();
        StringBuilder stringBuilder = new StringBuilder();
        identifiedkeywords.forEach(keyword -> stringBuilder.append(keywords.get(keyword)));
        response.setBody(stringBuilder.toString());
        response.setDate("2018-04-16");
        response.setUsername("BOT");
        return response;
    }
}
