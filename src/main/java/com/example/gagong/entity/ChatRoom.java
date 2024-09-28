package com.example.gagong.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chatRoom_id", nullable = false)
	private Long id;

	@OneToMany(mappedBy = "chatRoom")
	private List<Member> members = new ArrayList<>();

	@OneToOne(mappedBy = "inviteCode")
	private InviteCode inviteCode;

	public ChatRoom(InviteCode inviteCode, Member member) {
		this.inviteCode = inviteCode;
		updateChatRoom(member);
	}

	public void updateChatRoom(Member member) {
		this.members.add(member);
		member.addChatRoom(this);
	}

}
