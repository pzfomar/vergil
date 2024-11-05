package com.pzfomar.vergil.model.mongodb.auth;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.model.domain.auth.AuthEntity;
import com.pzfomar.vergil.model.domain.auth.AuthRepository;

import reactor.core.publisher.Mono;

@Repository
public interface AuthMongodbRepository extends ReactiveMongoRepository<AuthMongodbEntity, String>, AuthRepository {
    public default Mono<AuthEntity> modelSave(AuthEntity authModel) {
        return Mono.just(authModel)
                .map(AuthMongodbMapper::toEntity)
                .flatMap(this::save)
                .map(AuthMongodbMapper::toModel);
    }

    Mono<AuthMongodbEntity> findByEmail(String email);

    public default Mono<AuthEntity> modelFindByEmail(String email) {
        return this.findByEmail(email)
                .map(AuthMongodbMapper::toModel);
    }

    Mono<Boolean> existsByEmail(String email);

    public default Mono<Boolean> modelExistsByEmail(String email) {
        return this.existsByEmail(email);
    }
}
