package com.example.gagong.dto.request;

public record SignupRequest(
	String loginId,
	String nickname,
	String password,
	String role
) {

}
