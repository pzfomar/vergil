package com.pzfomar.vergil.application.account.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.pzfomar.vergil.application.account.dto.auth.SignInDto;
import com.pzfomar.vergil.infrastructure.config.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SignInService {
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private ReactiveAuthenticationManager authenticationManager;

	public Mono<SignInDto.Response> call(Mono<SignInDto.Request> request) {
		return request
				.flatMap(signIn -> this.authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()))
						.map(this.tokenProvider::createToken))
				.map(accessToken -> SignInDto.Response.builder().accessToken(accessToken).build());
	}
}
