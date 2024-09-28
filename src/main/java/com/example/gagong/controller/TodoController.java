package com.example.gagong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gagong.dto.request.CreateTodoRequest;
import com.example.gagong.dto.request.UpdateTodoRequest;
import com.example.gagong.dto.response.MainTodoResponse;
import com.example.gagong.dto.response.TodoResponse;
import com.example.gagong.entity.Member;
import com.example.gagong.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@GetMapping("/todos")
	public ResponseEntity<TodoResponse> getAllTodos(@AuthenticationPrincipal Member member) {
		return ResponseEntity.ok(todoService.getAllTodos(member));
	}

	@GetMapping("/main-todo")
	public ResponseEntity<MainTodoResponse> getTodosByMain(@AuthenticationPrincipal Member member) {
		return ResponseEntity.ok(todoService.getTodosByMain(member));
	}

	@PostMapping("/todo")
	public ResponseEntity<Boolean> create(@AuthenticationPrincipal Member member,
		@RequestBody CreateTodoRequest request) {

		return ResponseEntity.ok(todoService.create(member, request));
	}

	@PostMapping("/todo/{todoId}")
	public ResponseEntity<Boolean> updateManager(@PathVariable Long todoId, @AuthenticationPrincipal Member member) {
		return ResponseEntity.ok(todoService.updateManager(todoId, member));
	}

	@PatchMapping("/todo/{todoId}")
	public ResponseEntity<Boolean> updateTitle(@PathVariable Long todoId, @RequestBody UpdateTodoRequest request) {
		return ResponseEntity.ok(todoService.update(todoId, request));
	}

	@DeleteMapping("/todo/{todoId}")
	public ResponseEntity<Boolean> delete(@PathVariable Long todoId) {
		return ResponseEntity.ok(todoService.delete(todoId));
	}

	@PostMapping("/todo/{todoId}/complete")
	public ResponseEntity<Boolean> complete(@PathVariable Long todoId) {
		return ResponseEntity.ok(todoService.complete(todoId));
	}
}
