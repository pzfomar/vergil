package com.pzfomar.vergil.infrastructure.db.mongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.domain.model.AuthModel;
import com.pzfomar.vergil.domain.repository.AuthRepository;
import com.pzfomar.vergil.infrastructure.db.mongodb.entity.AuthEntity;
import com.pzfomar.vergil.infrastructure.db.mongodb.mapper.AuthMapper;

import reactor.core.publisher.Mono;

@Repository
public interface AuthMongodbRepository extends ReactiveMongoRepository<AuthEntity, String>, AuthRepository {
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
