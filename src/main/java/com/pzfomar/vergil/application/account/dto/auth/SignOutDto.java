package com.pzfomar.vergil.application.account.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SignOutDto {
	private SignOutDto() {
		throw new IllegalStateException("SignOutDto class");
	}

	@Setter
	@Getter
	@Builder
	public static class Request {}
}
