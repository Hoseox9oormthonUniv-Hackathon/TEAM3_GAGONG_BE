package com.example.gagong.inviteCode;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.gagong.common.InviteCodeFixture;
import com.example.gagong.common.MemberFixture;
import com.example.gagong.dto.request.CreateInviteCodeRequestDto;
import com.example.gagong.dto.response.InviteCodeResponseDto;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;
import com.example.gagong.repository.InviteCodeRepository;
import com.example.gagong.service.InviteCodeService;

@ExtendWith(MockitoExtension.class)
public class InviteCodeServiceTest {

	@Mock
	InviteCodeRepository inviteCodeRepository;

	@InjectMocks
	InviteCodeService inviteCodeService;

	@DisplayName("초대코드를 생성한다.")
	@Test
	void createInviteCode() {
		// given
		Member member = MemberFixture.member();
		InviteCode inviteCode = InviteCodeFixture.inviteCode();
		CreateInviteCodeRequestDto request = new CreateInviteCodeRequestDto("가족방");
		given(inviteCodeRepository.existsByCode(anyInt())).willReturn(false);
		given(inviteCodeRepository.save(any(InviteCode.class))).willReturn(inviteCode);

		// when
		InviteCodeResponseDto response = inviteCodeService.createInviteCode(request, member);

		// then
		assertThat(response.code()).isEqualTo(inviteCode.getCode());
	}
}
