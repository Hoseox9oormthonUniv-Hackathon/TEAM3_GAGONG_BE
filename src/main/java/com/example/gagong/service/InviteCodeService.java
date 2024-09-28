package com.example.gagong.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.dto.mapper.InviteCodeMapper;
import com.example.gagong.dto.request.ChatRoomRequest;
import com.example.gagong.dto.request.CreateInviteCodeRequestDto;
import com.example.gagong.dto.response.CreateInviteCodeResponseDto;
import com.example.gagong.dto.response.EntryInviteCodeResponse;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;
import com.example.gagong.repository.InviteCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InviteCodeService {

	private final InviteCodeRepository inviteCodeRepository;
	private final ChatRoomService chatRoomService;

	@Transactional
	public CreateInviteCodeResponseDto createInviteCode(
		CreateInviteCodeRequestDto requestDto,
		Member member
	) {
		try {
			int randomCode = createRandomCode();
			InviteCode newInviteCode = inviteCodeRepository.save(
				InviteCodeMapper.toInviteCode(requestDto, randomCode, member)
			);
			chatRoomService.createChatRoom(new ChatRoomRequest(randomCode, member));
			return new CreateInviteCodeResponseDto(newInviteCode.getCode());
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

	@Transactional
	public EntryInviteCodeResponse entryInviteCode(int code, Member member) {
		try {
			InviteCode inviteCode = getInviteCodeByCode(code);
			inviteCode.updateInviteCode(member);
			return new EntryInviteCodeResponse(true);
		} catch (Exception e) {
			throw new IllegalArgumentException("초대코드가 정상적이지 않습니다.");
		}
	}

	private InviteCode getInviteCodeByCode(int code) {
		return inviteCodeRepository.findByCode(code)
			.orElseThrow(() -> new NoSuchElementException("일치하는 초대코드를 찾을 수 없습니다."));
	}
}
