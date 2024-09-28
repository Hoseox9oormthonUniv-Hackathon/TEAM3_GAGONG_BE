package com.example.gagong.common;

import com.example.gagong.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberFixture {

	public static Member member() {
		return Member.builder()
			.loginId("test123")
			.password("test1234")
			.nickname("김회원")
			.build();
	}
}
