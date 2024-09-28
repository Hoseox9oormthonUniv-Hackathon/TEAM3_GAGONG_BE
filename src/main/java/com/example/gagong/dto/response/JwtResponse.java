package com.example.gagong.dto.response;

import lombok.Builder;

public record JwtResponse(
	String accessToken,
	String refreshToken,
	String tokenType
) {

	@Builder
	public JwtResponse(String accessToken, String refreshToken) {
		this(accessToken, refreshToken, "Bearer");
	}
}
