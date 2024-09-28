package com.example.gagong.ChatRoom;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.gagong.common.ChatRoomFixture;
import com.example.gagong.common.InviteCodeFixture;
import com.example.gagong.common.MemberFixture;
import com.example.gagong.dto.request.ChatRoomRequest;
import com.example.gagong.entity.ChatRoom;
import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Member;
import com.example.gagong.repository.ChatRoomRepository;
import com.example.gagong.repository.InviteCodeRepository;
import com.example.gagong.service.ChatRoomService;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceTest {

	@Mock
	ChatRoomRepository chatRoomRepository;

	@Mock
	InviteCodeRepository inviteCodeRepository;

	@InjectMocks
	ChatRoomService chatRoomService;

	@DisplayName("초대코드를 통해 채팅방을 생성한다.")
	@Test
	void createChatRoom() {
		// given
		Member member = MemberFixture.member();
		InviteCode inviteCode = InviteCodeFixture.inviteCode();
		ChatRoomRequest chatRoomRequest = new ChatRoomRequest(inviteCode.getCode(), member);

		given(inviteCodeRepository.findByCode(anyInt())).willReturn(Optional.of(inviteCode));

		// when
		chatRoomService.createChatRoom(chatRoomRequest);

		// then
		verify(chatRoomRepository).save(any(ChatRoom.class));
	}

	@DisplayName("초대코드를 통해 채팅방에 회원을 추가한다.")
	@Test
	void inviteMemberToChatRoom() {
		// given
		Member member1 = MemberFixture.member();
		Member member2 = MemberFixture.member();
		ReflectionTestUtils.setField(member1, "id", 1L);
		ReflectionTestUtils.setField(member2, "id", 2L);

		InviteCode inviteCode = InviteCodeFixture.inviteCode();
		ChatRoom chatRoom = ChatRoomFixture.chatRoom(inviteCode, new ArrayList<>(List.of(member1)));

		ChatRoomRequest chatRoomRequest = new ChatRoomRequest(inviteCode.getCode(), member2);

		given(inviteCodeRepository.findByCode(anyInt())).willReturn(Optional.of(inviteCode));
		given(chatRoomRepository.findByInviteCode(any())).willReturn(Optional.of(chatRoom));

		// when
		chatRoomService.inviteMemberToChatRoom(chatRoomRequest);

		// then
		assertThat(chatRoom.getMembers()).extracting("id")
			.containsExactlyInAnyOrder(
				1L,
				2L
			);
	}
}
