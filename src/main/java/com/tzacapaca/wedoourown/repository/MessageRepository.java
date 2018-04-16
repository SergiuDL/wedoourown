package com.tzacapaca.wedoourown.repository;

import com.tzacapaca.wedoourown.domain.Message;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Reactive mongo repository for {@link Message}s.
 * @author selascu
 */
@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String>, MessageRepositoryCustom {

	/**
	 * Finds all {@link Message}s for the given user.
	 * @param username - the username.
	 * @return a {@link Flux} of all the {@link Message}s for the user.
	 */
    Flux<Message> findAllByUsername(String username);
}
