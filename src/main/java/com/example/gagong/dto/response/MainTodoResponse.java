package com.example.gagong.dto.response;

import java.util.List;

import com.example.gagong.entity.Todo;

public record MainTodoResponse(
	List<Todo> todos
) {
	public static MainTodoResponse of(List<Todo> todos) {
		return new MainTodoResponse(todos);
	}
}
