package com.example.gagong.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.dto.mapper.InviteCodeMapper;
import com.example.gagong.dto.request.CreateInviteCodeRequestDto;
import com.example.gagong.dto.response.InviteCodeResponseDto;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;
import com.example.gagong.repository.InviteCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InviteCodeService {

	private InviteCodeRepository inviteCodeRepository;

	@Transactional
	public InviteCodeResponseDto createInviteCode(
		CreateInviteCodeRequestDto requestDto,
		Member member
	) {
		try {
			int randomCode = createRandomCode();
			InviteCode newInviteCode = inviteCodeRepository.save(
				InviteCodeMapper.toInviteCode(requestDto, randomCode, member)
			);
			return new InviteCodeResponseDto(newInviteCode.getCode());
		} catch (Exception e) {
			throw new IllegalArgumentException("초대코드 생성에 실패했습니다.");
		}
	}

	private int createRandomCode() {
		int code;
		do {
			code = (int)(Math.random() * 900000) + 100000; // 100000부터 999999까지의 6자리 숫자 생성
		} while (inviteCodeRepository.existsByCode(code));

		return code;
	}
}
