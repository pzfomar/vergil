package com.pzfomar.vergil.model.h2.auth;

import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.model.domain.auth.AuthEntity;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class AuthH2Repository {
    private final AuthH2Jpa authH2Jpa;

    public Mono<AuthEntity> save(AuthEntity authModel) {
        return Mono.just(authModel)
                .map(AuthH2Mapper::toEntity)
                .flatMap(this.authH2Jpa::save)
                .map(AuthH2Mapper::toModel);
    }

    public Mono<AuthEntity> findByEmail(String email) {
        return this.authH2Jpa.findByEmail(email)
                .map(AuthH2Mapper::toModel);
    }

    public Mono<Boolean> existsByEmail(String email) {
        return this.authH2Jpa.existsByEmail(email);
    }
}
