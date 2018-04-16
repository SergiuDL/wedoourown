package com.tzacapaca.wedoourown.repository;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tzacapaca.wedoourown.config.MongoConfig;
import com.tzacapaca.wedoourown.domain.Message;

/**
 * Custom repo implementation for {@link Message}s.
 * @author selascu
 *
 */
@Repository
public class MessageRepositoryImpl implements MessageRepositoryCustom {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    /**
     * {@inheritDoc}
     */
	@Override
	public Message findLatestPostByUser(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userName));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		List<Message> messagesForUser = mongoOperation.find(query, Message.class);
		return messagesForUser.get(0);
	}

}
