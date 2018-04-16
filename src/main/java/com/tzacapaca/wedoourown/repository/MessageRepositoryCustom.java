package com.tzacapaca.wedoourown.repository;

import org.springframework.stereotype.Repository;

import com.tzacapaca.wedoourown.domain.Message;

/**
 * Custom repository interface for {@link Message}.
 *
 * @author selascu
 */
@Repository
public interface MessageRepositoryCustom {
	
	/**
	 * Finds the latest post for a user.
	 * @param user - the user for which the post is retrieved.
	 * @return the latest message.
	 */
	public Message findLatestPostByUser(String user);

}
