package com.pzfomar.vergil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pzfomar.vergil.application.account.dto.auth.SignUpDto;
import com.pzfomar.vergil.application.account.service.auth.SignUpService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@SpringBootApplication
@AllArgsConstructor
public class VergilApplication implements CommandLineRunner {
	private final SignUpService signUpService;

	public static void main(String[] args) {
		SpringApplication.run(VergilApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Mono<SignUpDto.Request> request = Mono.just(SignUpDto.Request.builder()
				.email("demo")
				.password("demo")
				.confirmPassword("demo")
				.build());
		this.signUpService.call(request).map(voidd -> ResponseEntity.status(HttpStatus.CREATED).build()).block();
	}
}
