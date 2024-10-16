package com.pzfomar.vergil.infrastructure.config.security;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthRestController {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public Mono<ResponseEntity<Object>> signUp(@Valid @RequestBody Mono<SignUpRequest> request) {
        return request.doOnNext(signUp -> log.info("signUp:{}", signUp))
                .flatMap(signUp -> this.authRepository.existsByUsername(signUp.username()))
                .doOnNext(exists -> log.info("exists:{}", exists))
                .filter(exists -> !exists)
                .flatMap(e -> request)
                .map(signUp -> signUp.password().equals(signUp.confirmPassword()))
                .doOnNext(passwordEquals -> log.info("passwordEquals:{}", passwordEquals))
                .filter(passwordEquals -> passwordEquals)
                .flatMap(e -> request)
                .map(signUp -> AuthEntity.builder()
                        .username(signUp.username())
                        .password(passwordEncoder.encode(signUp.password()))
                        .build())
                .doOnNext(authEntity -> log.info("authEntity:{}", authEntity))
                .flatMap(this.authRepository::save)
                .doOnNext(authEntity -> log.info("authEntity:{}", authEntity))
                .map(authEntity -> ResponseEntity.status(HttpStatus.CREATED).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/sign-in")
    public Mono<ResponseEntity<SignInResponse>> signIn(@Valid @RequestBody Mono<SignInRequest> request) {
        return request
                .flatMap(signIn -> this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(signIn.username(), signIn.password()))
                        .map(this.tokenProvider::createToken))
                .map(jwt -> ResponseEntity.ok()
                        .headers(httpHeaders(jwt))
                        .body(SignInResponse.builder().accessToken(jwt).build()));
    }

    @PostMapping("/sign-out")
    public Mono<ResponseEntity<Object>> signOut(@Valid @RequestBody Mono<SignOutRequest> request) {
        return request.map(signOut -> ResponseEntity.ok().build());
    }

    public static record SignUpRequest(@JsonProperty("username") @NotBlank String username,
            @JsonProperty("password") @NotBlank String password,
            @JsonProperty("confirm_password") @NotBlank String confirmPassword) {
    }

    public static record SignInRequest(@JsonProperty("username") @NotBlank String username,
            @JsonProperty("password") @NotBlank String password) {
    }

    public static record SignOutRequest() {
    }

    @Builder
    public static record SignInResponse(@JsonProperty("access_token") String accessToken) {
    }

    private HttpHeaders httpHeaders(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        return httpHeaders;
    }
}
