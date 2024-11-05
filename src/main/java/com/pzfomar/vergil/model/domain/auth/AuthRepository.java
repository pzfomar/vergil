package com.pzfomar.vergil.model.domain.auth;

import reactor.core.publisher.Mono;

public interface AuthRepository {
	Mono<AuthEntity> modelSave(AuthEntity authModel);
	
	Mono<AuthEntity> modelFindByEmail(String email);

	Mono<Boolean> modelExistsByEmail(String email);
}
