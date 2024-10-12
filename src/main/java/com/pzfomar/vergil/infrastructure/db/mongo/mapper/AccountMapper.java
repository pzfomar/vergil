package com.pzfomar.vergil.infrastructure.db.mongo.mapper;

import com.pzfomar.vergil.domain.model.AccountModel;
import com.pzfomar.vergil.infrastructure.db.mongo.entity.AccountEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AccountMapper {
    public static AccountEntity toEntity(AccountModel data) {
        return AccountEntity.builder()
                .id(data.getId())
                .facebookId(data.getFacebookId())
                .email(data.getEmail())
                .password(data.getPassword())
                .build();
    }

    public static AccountModel toModel(AccountEntity data) {
        return AccountModel.builder()
                .id(data.getId())
                .facebookId(data.getFacebookId())
                .email(data.getEmail())
                .password(data.getPassword())
                .build();
    }

    public static Mono<AccountEntity> toEntity(Mono<AccountModel> data) {
        return data.map(AccountMapper::toEntity);
    }

    public static Mono<AccountModel> toModel(Mono<AccountEntity> data) {
        return data.map(AccountMapper::toModel);
    }

    public static Flux<AccountEntity> toEntity(Flux<AccountModel> data) {
        return data.map(AccountMapper::toEntity);
    }

    public static Flux<AccountModel> toModel(Flux<AccountEntity> data) {
        return data.map(AccountMapper::toModel);
    }
}
