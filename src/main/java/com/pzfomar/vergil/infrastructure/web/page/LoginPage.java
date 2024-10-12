package com.pzfomar.vergil.infrastructure.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class LoginPage {
    @GetMapping("/login.html")
    public Mono<String> login(Model model) {
        return Mono.just("login");
    }
}
