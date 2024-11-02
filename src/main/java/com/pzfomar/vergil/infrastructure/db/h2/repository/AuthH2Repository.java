package com.pzfomar.vergil.infrastructure.db.h2.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.domain.model.AuthModel;
import com.pzfomar.vergil.infrastructure.db.h2.entity.AuthEntity;
import com.pzfomar.vergil.infrastructure.db.h2.mapper.AuthMapper;

import reactor.core.publisher.Mono;

@Repository
public interface AuthH2Repository extends R2dbcRepository<AuthEntity, String> {
    public default Mono<AuthModel> modelSave(AuthModel authModel) {
        return Mono.just(authModel)
                .map(AuthMapper::toEntity)
                .flatMap(this::save)
                .map(AuthMapper::toModel);
    }

    Mono<AuthEntity> findByEmail(String email);

    public default Mono<AuthModel> modelFindByEmail(String email) {
        return this.findByEmail(email)
                .map(AuthMapper::toModel);
    }

    Mono<Boolean> existsByEmail(String email);

    public default Mono<Boolean> modelExistsByEmail(String email) {
        return this.existsByEmail(email);
    }
}
