package com.tzacapaca.wedoourown.controller;

import com.tzacapaca.wedoourown.domain.Message;
import com.tzacapaca.wedoourown.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for messages.
 * @author selascu
 */
@RequestMapping("/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Flux<Message> getAll() {
        return messageRepository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/message")
    Mono<Message> saveMessage(Message message){
    	return messageRepository.save(message);
    }
    
//    @RequestMapping(method = RequestMethod.PUT, value="/message")
//    Mono<Message> editMessage(Message message){
//    	
//    	
//    }
    @RequestMapping(method = RequestMethod.GET, value = "/latest/{userName}")
    Message findLatest(@PathVariable String userName){
    	return messageRepository.findLatestPostByUser(userName);
    }
    
}
