package com.example.gagong.dto.request;

public record CreateTodoRequest(
	String title,
	String content,
	Long managerId
) {
}
