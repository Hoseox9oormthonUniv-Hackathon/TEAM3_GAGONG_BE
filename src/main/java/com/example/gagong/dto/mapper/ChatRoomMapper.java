package com.example.gagong.dto.mapper;

import com.example.gagong.entity.ChatRoom;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomMapper {

	public static ChatRoom toChatRoom(InviteCode inviteCode, Member member) {
		return new ChatRoom(inviteCode, member);
	}
}
