package com.pzfomar.vergil.domain.repository;

import com.pzfomar.vergil.domain.model.AuthModel;

import reactor.core.publisher.Mono;

public interface AuthRepository {
	Mono<AuthModel> save(AuthModel authModel);
	
	Mono<AuthModel> findByEmail(String email);

	Mono<Boolean> existsByEmail(String email);
}
