package com.example.gagong.dto.mapper;

import com.example.gagong.dto.request.CreateTodoRequest;
import com.example.gagong.dto.request.UpdateTodoRequest;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;
import com.example.gagong.entity.Todo;

public class TodoMapper {

	public static Todo todoFromCreateTodoRequest(CreateTodoRequest request, Member author, InviteCode inviteCode) {
		return Todo.builder()
			.title(request.title())
			.author(author)
			.inviteCode(inviteCode)
			.build();
	}

	public static Todo update(Todo todo, UpdateTodoRequest request) {
		return Todo.builder()
			.title(request.title())
			.build();
	}

}
