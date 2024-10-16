package com.pzfomar.vergil.infrastructure.config.security;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthRepository extends ReactiveMongoRepository<AuthEntity, String> {
    Mono<AuthEntity> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
}
