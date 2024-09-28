package com.example.gagong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gagong.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	List<ChatMessage> findAllByChatRoomId(Long chatRoomId);
}
