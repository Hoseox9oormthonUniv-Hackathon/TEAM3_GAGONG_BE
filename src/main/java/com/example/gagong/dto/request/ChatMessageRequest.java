package com.example.gagong.dto.request;

public record ChatMessageRequest(
	String message,
	Long authorId
) {
}
