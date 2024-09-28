package com.example.gagong.dto.response;

public record ChatMessageResponse(

	Long authorId,
	String message,
	String createdAt
) {
}
