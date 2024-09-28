package com.example.gagong.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class InviteCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inviteCode_id", nullable = false)
	private Long id;

	@Column(name = "inviteCode", nullable = false, unique = true)
	private String inviteCode;

	@Column(name = "familyName", nullable = false)
	private String familyName;

	@OneToMany(mappedBy = "inviteCode")
	private List<Member> members = new ArrayList<>();

}
