package com.tzacapaca.wedoourown.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.Environment;

import org.springframework.data.mongodb.MongoDbFactory;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * Custom mongo configuration for mongo template queries.
 * 
 * @author selascu
 */
@Configuration
@PropertySource("application.properties")
public class MongoConfig {

	@Resource
	private Environment env;

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), env.getProperty("spring.data.mongodb.database"));
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}