package com.example.gagong.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message", nullable = false)
	private String message;

	private long chatRoomId;

	private long authorId;

	private LocalDateTime createdAt;

	public ChatMessage(String message, Long chatRoomId, Long authorId) {
		this.message = message;
		this.chatRoomId = chatRoomId;
		this.authorId = authorId;
		this.createdAt = LocalDateTime.now();
	}
}
