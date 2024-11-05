package com.pzfomar.vergil.service.signin;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.pzfomar.vergil.utility.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class SignInService {
	private final JwtTokenProvider tokenProvider;

	private final ReactiveAuthenticationManager authenticationManager;

	public Mono<SignInResponseDto> call(Mono<SignInRequestDto> request) {
		return request
				.flatMap(signIn -> this.authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()))
						.map(this.tokenProvider::createToken))
				.map(accessToken -> SignInResponseDto.builder().accessToken(accessToken).build());
	}
}
