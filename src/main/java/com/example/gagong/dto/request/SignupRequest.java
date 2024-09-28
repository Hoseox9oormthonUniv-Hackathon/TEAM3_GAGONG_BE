package com.example.gagong.dto.request;

import com.example.gagong.entity.Role;

public record SignupRequest(
	String loginId,
	String nickname,
	String password,
	Role role
) {

}
