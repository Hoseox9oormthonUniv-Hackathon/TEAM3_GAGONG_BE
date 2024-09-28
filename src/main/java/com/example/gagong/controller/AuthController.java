package com.example.gagong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.request.ReissueRequest;
import com.example.gagong.dto.request.SigninRequest;
import com.example.gagong.dto.request.SignupRequest;
import com.example.gagong.dto.response.JwtResponse;
import com.example.gagong.entity.Member;
import com.example.gagong.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/sign-up")
	public ResponseEntity<Boolean> create(@RequestBody SignupRequest request) {
		return ResponseEntity.ok(authService.signup(request));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<JwtResponse> login(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authService.signin(request));
	}

	@PostMapping("/reissue")
	public ResponseEntity<JwtResponse> reissue(@RequestBody ReissueRequest request) {
		return ResponseEntity.ok(authService.reissue(request));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> delete(@AuthenticationPrincipal Member member) {
		return ResponseEntity.ok(authService.delete(member));
	}
}
