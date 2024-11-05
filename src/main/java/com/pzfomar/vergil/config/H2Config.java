package com.pzfomar.vergil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.pzfomar.vergil.model.h2.*")
@EnableR2dbcAuditing
@RequiredArgsConstructor
public class H2Config {
    private final ConnectionFactory connectionFactory;

    @Bean
    ConnectionFactoryInitializer connectionFactoryInitializer() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(this.connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator());
        return initializer;
    }
}
