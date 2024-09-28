package com.example.gagong.common;

import java.util.List;

import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InviteCodeFixture {

	public static InviteCode inviteCode() {
		return InviteCode.builder()
			.code(123456)
			.build();
	}

	public static InviteCode inviteCode2(List<Member> members) {
		return InviteCode.builder()
			.code(123456)
			.members(members)
			.build();
	}
}
