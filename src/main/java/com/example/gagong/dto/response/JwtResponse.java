package com.example.gagong.dto.response;

import lombok.Builder;

public record JwtResponse(
	String accessToken,
	String refreshToken,
	String tokenType,
	boolean isInviteCode
) {

	@Builder
	public JwtResponse(String accessToken, String refreshToken, boolean isInviteCode) {
		this(accessToken, refreshToken, "Bearer", isInviteCode);
	}
}
