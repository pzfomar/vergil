package com.pzfomar.vergil.domain.repository;

import com.pzfomar.vergil.domain.model.AccountModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    public Mono<AccountModel> findById(String id);
    public Flux<AccountModel> findAll();
    public Mono<AccountModel> save(AccountModel account);
}
