package com.pzfomar.vergil.service.signout;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class SignOutService {
	public Mono<Void> call(Mono<SignOutRequestDto> request) {
		return request.doOnNext(signOut -> log.info("signUp:{}", signOut))
				.then();
	}
}
