package com.pzfomar.vergil.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.pzfomar.vergil.domain.repository.AuthRepository;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http,
            JwtTokenProvider tokenProvider,
            ReactiveAuthenticationManager reactiveAuthenticationManager) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it -> it
                        //.pathMatchers("/**").authenticated()
                        .pathMatchers(HttpMethod.GET, "/#").permitAll()
                        .pathMatchers(HttpMethod.GET, "/#/").permitAll()
                        .pathMatchers(HttpMethod.GET, "/#/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                        .pathMatchers(HttpMethod.POST, "/auth/sign-in").permitAll()
                        .pathMatchers(HttpMethod.GET, "/webjars/swagger-ui/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        /*
                        .pathMatchers(HttpMethod.POST, "/**").authenticated()
                        .pathMatchers(HttpMethod.GET, "/**").authenticated()
                        .pathMatchers(HttpMethod.PUT, "/**").authenticated()
                        .pathMatchers(HttpMethod.PATCH, "/**").authenticated()
                        .pathMatchers(HttpMethod.DELETE, "/**").authenticated()
                        .pathMatchers(HttpMethod.GET, PATH_POSTS).permitAll()
                        .pathMatchers(HttpMethod.DELETE, PATH_POSTS).hasRole("ADMIN")
                        .pathMatchers(PATH_POSTS).authenticated()
                        .pathMatchers("/me").authenticated()
                        */
                        .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                        .anyExchange().permitAll())
                .addFilterAt(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication,
            AuthorizationContext context) {
        return authentication.map(a -> context.getVariables().get("user").equals(a.getName()))
                .map(AuthorizationDecision::new);
    }

    @Bean
    ReactiveUserDetailsService userDetailsService(AuthRepository authRepository) {
        return email -> authRepository.findByEmail(email)
                .map(u -> User.withUsername(u.getEmail())
                        .password(u.getPassword())
                        .authorities(List.of(u.getRol().name()).toArray(new String[0]))
                        .accountExpired(u.getStatus().name() != "ACTIVE")
                        .credentialsExpired(u.getStatus().name() != "ACTIVE")
                        .disabled(u.getStatus().name() != "ACTIVE")
                        .accountLocked(u.getStatus().name() != "ACTIVE")
                        .build());
    }

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }
}
