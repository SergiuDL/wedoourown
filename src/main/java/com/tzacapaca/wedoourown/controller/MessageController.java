package com.tzacapaca.wedoourown.controller;

import com.tzacapaca.wedoourown.domain.Message;
import com.tzacapaca.wedoourown.repository.MessageRepository;
import com.tzacapaca.wedoourown.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for {@link Message}s.
 * @author selascu
 */
@RequestMapping(value = "/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;


    /**
     * Find all messages.
     * @return {@link Flux} with all the {@link Message}s.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Flux<Message> getAll() {
        return messageService.findAll();
    }
    
    /**
     * Save a new message.
     * @param message - the {@link Message} to save.
     * @return the saved {@link Message}.
     */
    @RequestMapping(method = RequestMethod.POST, value="/message")
    Mono<Message> saveMessage(Message message){
    	return messageRepository.insert(message);
    }
    
    /**
     * Edits the last {@link Message} for the user.
     * @param message - the edited {@link Message}.
     * @return the edited {@link Message}.
     */
    @RequestMapping(method = RequestMethod.PUT, value="/message", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    Mono<Message> editMessage(@RequestBody Message message){
    	Message latestMessageForUser = findLatest(message.getUsername());
    	latestMessageForUser.setBody(message.getBody());
    	latestMessageForUser.setDate(message.getDate());
    	return messageRepository.save(latestMessageForUser);
    } 


    /**
     * Find the latest {@link Message} for the given user.
     * @param userName - the user name
     * @return the {@link Message}.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/latest/{userName}")
    Message findLatest(@PathVariable String userName){
    	return messageRepository.findLatestPostByUser(userName);
    }

    /**
     * Removes the last {@link Message} for the given user.
     * @param userName - the user name.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{userName}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    void removeLastMessage(@PathVariable String userName) {
        messageService.removeLastMessage(userName);
    }

    /**
     * Retrieves all {@link Message} for a user.
     * @param userName - the user name of the user.
     * @return a {@link Flux} of all the {@link Message}s for the given user. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/allByUser/{userName}")
    Flux<Message> getAllMessagesByUser(@PathVariable String userName) {
        return messageService.getAllByUserName(userName);
    }
    
}
