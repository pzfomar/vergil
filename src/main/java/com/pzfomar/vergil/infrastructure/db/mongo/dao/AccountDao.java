package com.pzfomar.vergil.infrastructure.db.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.domain.model.AccountModel;
import com.pzfomar.vergil.domain.repository.AccountRepository;
import com.pzfomar.vergil.infrastructure.db.mongo.entity.AccountEntity;
import com.pzfomar.vergil.infrastructure.db.mongo.mapper.AccountMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountDao implements AccountRepository {
    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<AccountModel> findById(String id) {
        return AccountMapper.toModel(template.findById(id, AccountEntity.class));
    }
 
    @Override
    public Flux<AccountModel> findAll() {
        return AccountMapper.toModel(template.findAll(AccountEntity.class));
    } 

    @Override
    public Mono<AccountModel> save(AccountModel account) {
        return AccountMapper.toModel(template.save(AccountMapper.toEntity(account)));
    }
}
