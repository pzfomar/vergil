package com.pzfomar.vergil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.pzfomar.vergil.model.mongodb.*")
@EnableReactiveMongoAuditing
@RequiredArgsConstructor
public class MongodbConfig {
    private final MongoClient mongoClient;

    @Value("${spring.data.mongodb.database:vergil}")
    private String springDataMongodbDatabase;

    @Bean
    ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(this.mongoClient, springDataMongodbDatabase);
    }
}
