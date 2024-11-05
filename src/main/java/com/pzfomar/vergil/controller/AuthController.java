package com.pzfomar.vergil.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pzfomar.vergil.service.signin.SignInRequestDto;
import com.pzfomar.vergil.service.signin.SignInResponseDto;
import com.pzfomar.vergil.service.signin.SignInService;
import com.pzfomar.vergil.service.signout.SignOutRequestDto;
import com.pzfomar.vergil.service.signout.SignOutService;
import com.pzfomar.vergil.service.signup.SignUpRequestDto;
import com.pzfomar.vergil.service.signup.SignUpService;

import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.POST }, allowedHeaders = { "Authorization", "Content-Type", "Accept" })
@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
@AllArgsConstructor
public class AuthController {
	private final SignUpService signUpService;

	private final SignInService signInService;

	private final SignOutService signOutService;

	@PostMapping("/sign-up")
	public Mono<ResponseEntity<Void>> signUp(@Valid @RequestBody Mono<SignUpRequestDto> request) {
		return this.signUpService.call(request).map(voidd -> ResponseEntity.status(HttpStatus.CREATED).build());
	}

	@PostMapping("/sign-in")
	public Mono<ResponseEntity<SignInResponseDto>> signIn(@Valid @RequestBody Mono<SignInRequestDto> request) {
		return this.signInService.call(request).map(response -> {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + response.getAccessToken());
			return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
		});
	}

	@PostMapping("/sign-out")
	public Mono<ResponseEntity<Void>> signOut(@Valid @RequestBody Mono<SignOutRequestDto> request) {
		return this.signOutService.call(request).map(voidd -> ResponseEntity.status(HttpStatus.OK).build());
	}
}
