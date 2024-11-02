package com.pzfomar.vergil.domain.repository;

import com.pzfomar.vergil.domain.model.AuthModel;

import reactor.core.publisher.Mono;

public interface AuthRepository {
	Mono<AuthModel> modelSave(AuthModel authModel);
	
	Mono<AuthModel> modelFindByEmail(String email);

	Mono<Boolean> modelExistsByEmail(String email);
}
