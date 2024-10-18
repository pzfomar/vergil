package com.pzfomar.vergil.infrastructure.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pzfomar.vergil.application.account.dto.auth.SignInDto;
import com.pzfomar.vergil.application.account.dto.auth.SignOutDto;
import com.pzfomar.vergil.application.account.dto.auth.SignUpDto;
import com.pzfomar.vergil.application.account.service.auth.SignInService;
import com.pzfomar.vergil.application.account.service.auth.SignUpService;

import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthRestController {
	@Autowired
	private SignUpService signUpService;

	@Autowired
	private SignInService signInService;

	@PostMapping("/sign-up")
	public Mono<ResponseEntity<Void>> signUp(@Valid @RequestBody Mono<SignUpDto.Request> request) {
		return this.signUpService.call(request).map(voidd -> ResponseEntity.status(HttpStatus.CREATED).build());
	}

	@PostMapping("/sign-in")
	public Mono<ResponseEntity<SignInDto.Response>> signIn(@Valid @RequestBody Mono<SignInDto.Request> request) {
		return this.signInService.call(request).map(response -> {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + response.getAccessToken());
			return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
		});
	}

	@PostMapping("/sign-out")
	public Mono<ResponseEntity<Void>> signOut(@Valid @RequestBody Mono<SignOutDto.Request> request) {
		return request.map(signOut -> ResponseEntity.status(HttpStatus.OK).build());
	}
}
