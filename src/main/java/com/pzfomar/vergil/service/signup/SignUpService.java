package com.pzfomar.vergil.service.signup;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pzfomar.vergil.model.domain.auth.AuthEntity;
import com.pzfomar.vergil.model.domain.auth.AuthRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class SignUpService {
    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    public Mono<Void> call(Mono<SignUpRequestDto> request) {
        return request.doOnNext(signUp -> log.info("signUp:{}", signUp))
                .flatMap(signUp -> this.authRepository.modelExistsByEmail(signUp.getEmail()))
                .doOnNext(exists -> log.info("exists:{}", exists))
                .filter(exists -> !exists)
                .flatMap(e -> request)
                .map(signUp -> signUp.getPassword().equals(signUp.getConfirmPassword()))
                .doOnNext(passwordEquals -> log.info("passwordEquals:{}", passwordEquals))
                .filter(passwordEquals -> passwordEquals)
                .flatMap(e -> request)
                .map(signUp -> AuthEntity.builder()
                        .email(signUp.getEmail())
                        .password(passwordEncoder.encode(signUp.getPassword()))
                        .build())
                .doOnNext(auth -> log.info("auth:{}", auth))
                .flatMap(this.authRepository::modelSave)
                .doOnNext(authEntity -> log.info("authEntity:{}", authEntity))
                .then();
    }
}
