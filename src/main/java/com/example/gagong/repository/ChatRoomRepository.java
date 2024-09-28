package com.example.gagong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gagong.entity.ChatRoom;
import com.example.gagong.entity.InviteCode;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	Optional<ChatRoom> findByInviteCode(InviteCode inviteCode);
}
