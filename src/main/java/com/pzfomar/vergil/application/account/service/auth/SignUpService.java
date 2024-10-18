package com.pzfomar.vergil.application.account.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pzfomar.vergil.application.account.dto.auth.SignUpDto;
import com.pzfomar.vergil.domain.model.AuthModel;
import com.pzfomar.vergil.domain.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SignUpService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Mono<Void> call(Mono<SignUpDto.Request> request) {
        return request.doOnNext(signUp -> log.info("signUp:{}", signUp))
                .flatMap(signUp -> this.authRepository.existsByEmail(signUp.getEmail()))
                .doOnNext(exists -> log.info("exists:{}", exists))
                .filter(exists -> !exists)
                .flatMap(e -> request)
                .map(signUp -> signUp.getPassword().equals(signUp.getConfirmPassword()))
                .doOnNext(passwordEquals -> log.info("passwordEquals:{}", passwordEquals))
                .filter(passwordEquals -> passwordEquals)
                .flatMap(e -> request)
                .map(signUp -> AuthModel.builder()
                        .email(signUp.getEmail())
                        .password(passwordEncoder.encode(signUp.getPassword()))
                        .build())
                .doOnNext(auth -> log.info("auth:{}", auth))
                .flatMap(this.authRepository::save)
                .doOnNext(authEntity -> log.info("authEntity:{}", authEntity))
                .then();
    }
}
