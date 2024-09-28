package com.example.gagong.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Member implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", nullable = false)
	public Long id;

	@Column(name = "login_id", nullable = false, unique = true)
	private String loginId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatRoom_id")
	private ChatRoom chatRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inviteCode_id")
	private InviteCode inviteCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendar_id")
	private Calendar calendar;

	public void addChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public void addInviteCode(InviteCode inviteCode) {
		this.inviteCode = inviteCode;

  @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
