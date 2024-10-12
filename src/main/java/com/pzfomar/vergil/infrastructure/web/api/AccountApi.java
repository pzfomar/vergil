package com.pzfomar.vergil.infrastructure.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pzfomar.vergil.application.account.dto.RegisterEmailDto;
import com.pzfomar.vergil.application.account.service.RegisterEmailService;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/account")
public class AccountApi {

    @Autowired
    private RegisterEmailService registerEmailService;

    @PostMapping("/register-email.json")
    public Mono<ResponseEntity<RegisterEmailDto.Response>> registerEmail(@RequestBody RegisterEmailDto.Request request) throws Exception {
        return registerEmailService.bussines(request);
    }

    @PostMapping("/register-facebook.json")
    public Mono<ResponseEntity<?>> registerFacebook(@RequestBody String request) {
        return null;
    }
}
