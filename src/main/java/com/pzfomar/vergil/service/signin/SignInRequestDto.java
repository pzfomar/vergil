package com.pzfomar.vergil.service.signin;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDto {
	@JsonProperty("email")
	@NotBlank
	private String email;

	@JsonProperty("password")
	@NotBlank
	private String password;
}
