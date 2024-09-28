package com.example.gagong.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Todo extends TimeBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
	private boolean completed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inviteCode_id")
	private InviteCode inviteCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Member author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Member manager;

	public Todo complete() {
		this.completed = true;
		return this;
	}

	public Todo addManager(Member member) {
		this.manager = member;
		return this;
	}

	public Todo updateTitle(String title) {
		this.title = title;
		return this;
	}
}
