package com.example.gagong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.response.ChatRoomIdResponseDto;
import com.example.gagong.entity.Member;
import com.example.gagong.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	@GetMapping("/api/chat-room")
	public ResponseEntity<ChatRoomIdResponseDto> getChatRoomId(
		@AuthenticationPrincipal Member member
	) {
		ChatRoomIdResponseDto response = chatRoomService.getChatRoomId(member);

		return ResponseEntity.ok(response);
	}
}
