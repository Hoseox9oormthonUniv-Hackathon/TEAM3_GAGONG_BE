package com.example.gagong.common;

import java.util.List;

import com.example.gagong.entity.ChatRoom;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomFixture {

	public static ChatRoom chatRoom(InviteCode inviteCode, List<Member> members) {
		return ChatRoom.builder()
			.inviteCode(inviteCode)
			.members(members)
			.build();
	}
}
