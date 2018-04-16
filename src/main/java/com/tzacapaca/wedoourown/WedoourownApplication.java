package com.tzacapaca.wedoourown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

//@ComponentScan("com.tzacapaca.wedoourown")
@SpringBootApplication()
//@EnableCassandraRepositories
public class WedoourownApplication {
	public static void main(String[] args) {
		SpringApplication.run(WedoourownApplication.class, args);
	}
}
