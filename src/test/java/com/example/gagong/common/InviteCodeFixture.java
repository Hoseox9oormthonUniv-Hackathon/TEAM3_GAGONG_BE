package com.example.gagong.common;

import com.example.gagong.entity.InviteCode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InviteCodeFixture {

	public static InviteCode inviteCode() {
		return InviteCode.builder()
			.code("123456")
			.build();
	}
}
