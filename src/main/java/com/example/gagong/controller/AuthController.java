package com.example.gagong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.request.SigninRequest;
import com.example.gagong.dto.request.SignupRequest;
import com.example.gagong.dto.response.JwtResponse;
import com.example.gagong.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/sign-up")
	public ResponseEntity create(@RequestBody SignupRequest request) {
		authService.signup(request);
		return null;
	}

	@PostMapping("/sign-in")
	public ResponseEntity<JwtResponse> login(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authService.signin(request));
	}
}
