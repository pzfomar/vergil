package com.pzfomar.vergil.application.account.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SignUpDto {
	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Request {
			@JsonProperty("email")
			@NotBlank
			private String email;

			@JsonProperty("password")
			@NotBlank
			private String password;

			@JsonProperty("confirm_password")
			@NotBlank
			private String confirmPassword;
	}
}
