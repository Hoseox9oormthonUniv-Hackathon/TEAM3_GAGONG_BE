package com.example.gagong.dto.request;

import com.example.gagong.entity.Member;

public record ChatRoomRequest(
	int code,
	Member member
) {
}
