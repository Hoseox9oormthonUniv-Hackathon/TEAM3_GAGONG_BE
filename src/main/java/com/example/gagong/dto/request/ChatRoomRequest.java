package com.example.gagong.dto.request;

import com.example.gagong.entity.Member;

public record ChatRoomRequest(
	String code,
	Member member
) {
}
