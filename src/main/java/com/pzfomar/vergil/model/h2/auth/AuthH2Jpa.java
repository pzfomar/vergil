package com.pzfomar.vergil.model.h2.auth;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface AuthH2Jpa extends R2dbcRepository<AuthH2Entity, String> {
    Mono<AuthH2Entity> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);
}
