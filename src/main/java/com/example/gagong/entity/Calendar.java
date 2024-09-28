package com.example.gagong.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "eventDay", nullable = false)
	private LocalDateTime eventDay;

	@Column(name = "imageURL", nullable = false)
	private String image_url;

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "inviteCode_id", nullable = false)
	private InviteCode inviteCode;

	@OneToMany(mappedBy = "calendar")
	private List<Member> members = new ArrayList<>();

}
