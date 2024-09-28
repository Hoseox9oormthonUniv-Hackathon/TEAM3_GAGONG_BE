package com.example.gagong.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.dto.mapper.TodoMapper;
import com.example.gagong.dto.request.CreateTodoRequest;
import com.example.gagong.dto.request.UpdateTodoRequest;
import com.example.gagong.dto.response.MainTodoResponse;
import com.example.gagong.dto.response.TodoResponse;
import com.example.gagong.entity.Member;
import com.example.gagong.entity.Todo;
import com.example.gagong.repository.MemberRepository;
import com.example.gagong.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

	private final TodoRepository todoRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public boolean create(Member author, CreateTodoRequest request) {
		todoRepository.save(TodoMapper.todoFromCreateTodoRequest(request, author, author.getInviteCode()));
		return true;
	}

	public TodoResponse getAllTodos(Member member) {
		Member loadMember = memberRepository.findById(member.getId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

		LocalDate today = LocalDate.now();
		List<Todo> todayTodos = todoRepository.findByInviteCodeToday(loadMember.getInviteCode().getId(), today);
		List<Todo> pastTodos = todoRepository.findAllBeforeCurrentDate(loadMember.getInviteCode().getId(), today);

		System.out.println("asdasdsd : " + loadMember.getInviteCode().getId());
		System.out.println(todayTodos.toString());
		System.out.println(pastTodos.toString());

		return TodoResponse.of(todayTodos, pastTodos);
	}

	public MainTodoResponse getTodosByMain(Member member) {
		Pageable pageable = PageRequest.of(0, 3);
		List<Todo> mainTodos = todoRepository.mainTodoList(member.getInviteCode().getId(), pageable);

		return MainTodoResponse.of(mainTodos);
	}

	@Transactional
	public boolean update(Long todoId, UpdateTodoRequest request) {
		Todo todo = todoRepository.findById(todoId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

		todoRepository.save(todo.updateTitle(request.title()));
		return true;
	}

	@Transactional
	public boolean delete(Long todoId) {
		todoRepository.deleteById(todoId);

		return true;
	}

	@Transactional
	public boolean complete(Long todoId) {
		Todo todo = todoRepository.findById(todoId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

		todoRepository.save(todo.complete());
		return true;
	}

	@Transactional
	public Boolean updateManager(Long todoId, Member member) {
		Todo todo = todoRepository.findById(todoId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

		todoRepository.save(todo.addManager(member));
		return true;
	}
}