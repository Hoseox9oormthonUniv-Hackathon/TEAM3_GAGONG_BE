package com.example.gagong.dto.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gagong.dto.request.SignupRequest;
import com.example.gagong.entity.Member;

public class UserMapper {

	public static Member memberFromSignupRequest(SignupRequest request, PasswordEncoder passwordEncoder) {
		return Member.builder()
			.loginId(request.loginId())
			.nickname(request.nickname())
			.password(passwordEncoder.encode(request.password()))
			.role(request.role())
			.build();
	}
}
