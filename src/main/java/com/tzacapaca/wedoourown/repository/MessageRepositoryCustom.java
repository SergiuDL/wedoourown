package com.tzacapaca.wedoourown.repository;

import org.springframework.stereotype.Repository;

import com.tzacapaca.wedoourown.domain.Message;

@Repository
public interface MessageRepositoryCustom {
	
	public Message findLatestPostByUser(String user);

}
