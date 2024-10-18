package com.pzfomar.vergil.infrastructure.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pzfomar.vergil.domain.model.AuthModel;
import com.pzfomar.vergil.domain.repository.AuthRepository;
import com.pzfomar.vergil.infrastructure.mongo.dao.AuthDao;
import com.pzfomar.vergil.infrastructure.mongo.mapper.AuthMapper;

import reactor.core.publisher.Mono;

@Repository
public class AuthRepositoryImpl implements AuthRepository {
	@Autowired
	private AuthDao authDao;

	@Override
	public Mono<AuthModel> save(AuthModel authModel) {
		return Mono.just(authModel)
				.map(AuthMapper::toEntity)
				.flatMap(this.authDao::save)
				.map(AuthMapper::toModel);
	}

	@Override
	public Mono<AuthModel> findByEmail(String email) {
		return this.authDao.findByEmail(email)
				.map(AuthMapper::toModel);
	}

	@Override
	public Mono<Boolean> existsByEmail(String email) {
		return this.authDao.existsByEmail(email);
	}
}
