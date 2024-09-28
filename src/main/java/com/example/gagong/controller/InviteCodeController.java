package com.example.gagong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.request.CreateInviteCodeRequestDto;
import com.example.gagong.dto.response.CreateInviteCodeResponseDto;
import com.example.gagong.entity.Member;
import com.example.gagong.service.InviteCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InviteCodeController {

	private InviteCodeService inviteCodeService;

	@PostMapping("/api/invite-code")
	public ResponseEntity<CreateInviteCodeResponseDto> createInviteCode(
		@RequestBody CreateInviteCodeRequestDto request,
		@AuthenticationPrincipal Member member
	) {
		CreateInviteCodeResponseDto response = inviteCodeService.createInviteCode(request, member);

		return ResponseEntity.ok(response);
	}
}
