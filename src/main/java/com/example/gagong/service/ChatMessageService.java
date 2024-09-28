package com.example.gagong.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.dto.mapper.ChatMessageMapper;
import com.example.gagong.dto.request.ChatMessageRequest;
import com.example.gagong.dto.response.ChatMessageResponse;
import com.example.gagong.entity.ChatMessage;
import com.example.gagong.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;

	@Transactional
	public ChatMessageResponse saveChatMessage(
		ChatMessageRequest request,
		Long chatRoomId
	) {
		ChatMessage chatMessage = chatMessageRepository.save(
			ChatMessageMapper.toChatMessage(request, chatRoomId));
		return ChatMessageMapper.toChatMessageResponse(chatMessage);
	}

	public List<ChatMessageResponse> getChatMessages(Long chatRoomId) {
		List<ChatMessage> chatMessages = chatMessageRepository.findAllByChatRoomId(chatRoomId);
		return ChatMessageMapper.toChatMessageResponses(chatMessages);
	}
}
