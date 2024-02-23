package com.dnd.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {

	private String accessToken;

	public static LoginResponse from(String accessToken) {
		return LoginResponse.builder()
			.accessToken(accessToken)
			.build();
	}
}
