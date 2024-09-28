package com.example.gagong.dto.mapper;

import com.example.gagong.dto.request.CreateInviteCodeRequestDto;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InviteCodeMapper {

	public static InviteCode toInviteCode(
		CreateInviteCodeRequestDto requestDto,
		int code,
		Member member
	) {
		return new InviteCode(code, requestDto.familyName(), member);
	}
}
