package com.example.gagong.dto.response;

import java.util.List;

import com.example.gagong.entity.Todo;

public record TodoResponse(
	List<TodoOne> todayTodos,
	List<Todo> pastTodos
) {

	public static TodoResponse of(List<TodoOne> todayTodos, List<Todo> pastTodos) {
		return new TodoResponse(todayTodos, pastTodos);
	}
}
