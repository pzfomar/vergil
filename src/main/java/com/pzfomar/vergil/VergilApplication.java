package com.pzfomar.vergil;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class VergilApplication {
    @Value("${application.timezone:UTC}")
    private String applicationTimeZone;

	public static void main(String[] args) {
		SpringApplication.run(VergilApplication.class, args);
	}
	
	@PostConstruct
    public void executeAfterMain() {
        TimeZone.setDefault(TimeZone.getTimeZone(applicationTimeZone));
    }
}
