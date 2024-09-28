package com.example.gagong.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.gagong.dto.request.ChatMessageRequest;
import com.example.gagong.dto.response.ChatMessageResponse;
import com.example.gagong.entity.ChatMessage;

public class ChatMessageMapper {
	public static ChatMessage toChatMessage(
		ChatMessageRequest request,
		Long chatRoomId
	) {
		return new ChatMessage(request.message(), chatRoomId, request.authorId());
	}

	public static ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage) {
		return new ChatMessageResponse(
			chatMessage.getAuthorId(),
			chatMessage.getMessage(),
			chatMessage.getCreatedAt().toString()
		);
	}

	public static List<ChatMessageResponse> toChatMessageResponses(List<ChatMessage> chatMessages) {
		List<ChatMessageResponse> responses = new ArrayList<>();
		for (ChatMessage chatMessage : chatMessages) {
			responses.add(toChatMessageResponse(chatMessage));
		}
		return responses;
	}

}
