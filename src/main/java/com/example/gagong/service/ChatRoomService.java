package com.example.gagong.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.dto.mapper.ChatRoomMapper;
import com.example.gagong.dto.request.ChatRoomRequest;
import com.example.gagong.entity.ChatRoom;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.repository.ChatRoomRepository;
import com.example.gagong.repository.InviteCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

	private ChatRoomRepository chatRoomRepository;
	private InviteCodeRepository inviteCodeRepository;

	@Transactional
	public void createChatRoom(ChatRoomRequest request) {
		InviteCode inviteCode = getInviteByInviteCode(request.code());
		chatRoomRepository.save(ChatRoomMapper.toChatRoom(inviteCode, request.member()));
	}

	@Transactional
	public void inviteMemberToChatRoom(ChatRoomRequest request) {
		InviteCode inviteCode = getInviteByInviteCode(request.code());
		ChatRoom chatRoom = getChatRoomByInviteCode(inviteCode);
		chatRoom.updateChatRoom(request.member());
	}

	private InviteCode getInviteByInviteCode(String code) {
		return inviteCodeRepository.findByCode(code)
			.orElseThrow(() -> new RuntimeException("초대코드가 일치하지 않습니다."));
	}

	private ChatRoom getChatRoomByInviteCode(InviteCode inviteCode) {
		return chatRoomRepository.findByInviteCode(inviteCode)
			.orElseThrow(() -> new RuntimeException("초대코드와 일치하는 채팅방이 존재하지 않습니다."));
	}
}
