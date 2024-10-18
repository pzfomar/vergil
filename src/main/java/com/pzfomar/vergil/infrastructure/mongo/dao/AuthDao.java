package com.pzfomar.vergil.infrastructure.mongo.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.pzfomar.vergil.infrastructure.mongo.entity.AuthEntity;

import reactor.core.publisher.Mono;

public interface AuthDao extends ReactiveMongoRepository<AuthEntity, String> {
    Mono<AuthEntity> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);
}
