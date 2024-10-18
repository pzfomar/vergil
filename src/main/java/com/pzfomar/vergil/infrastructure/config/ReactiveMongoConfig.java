package com.pzfomar.vergil.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.pzfomar.vergil.*")
@EnableReactiveMongoAuditing
public class ReactiveMongoConfig {
    @Value("${spring.data.mongodb.database:vergil}")
    private String springDataMongodbDatabase;

    @Autowired
    private MongoClient mongoClient;

    @Bean
    ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, springDataMongodbDatabase);
    }
}
