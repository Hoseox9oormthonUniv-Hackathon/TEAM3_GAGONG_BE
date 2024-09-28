package com.example.gagong.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.request.ChatMessageRequest;
import com.example.gagong.dto.response.ChatMessageResponse;
import com.example.gagong.service.ChatMessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

	private final ChatMessageService chatMessageService;

	@MessageMapping("/chat-rooms/{chatRoomId}")
	@SendTo("/sub/chat-rooms/{chatRoomId}")
	public ChatMessageResponse sendMessage(
		@DestinationVariable("chatRoomId") Long chatRoomId,
		@Payload ChatMessageRequest request
	) {
		return chatMessageService.saveChatMessage(request, chatRoomId);
	}

	@GetMapping("/api/chat-messages/{chatRoomId}")
	public ResponseEntity<List<ChatMessageResponse>> getChatMessages(
		@PathVariable("chatRoomId") Long chatRoomId
	) {
		List<ChatMessageResponse> response = chatMessageService.getChatMessages(chatRoomId);

		return ResponseEntity.ok(response);
	}
}
