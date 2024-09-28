package com.example.gagong.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gagong.config.jwt.JwtProvider;
import com.example.gagong.dto.mapper.UserMapper;
import com.example.gagong.dto.request.ReissueRequest;
import com.example.gagong.dto.request.SigninRequest;
import com.example.gagong.dto.request.SignupRequest;
import com.example.gagong.dto.response.JwtResponse;
import com.example.gagong.entity.Member;
import com.example.gagong.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Transactional
	public boolean signup(SignupRequest request) {
		if (memberRepository.existsByLoginId(request.loginId())) {
			throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
		}

		memberRepository.save(UserMapper.memberFromSignupRequest(request, passwordEncoder));

		return true;
	}

	public JwtResponse signin(SigninRequest request) {
		Member member = memberRepository.findByLoginId(request.loginId())
			.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

		if (!passwordEncoder.matches(request.password(), member.getPassword()))
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");

		if (member.getInviteCode() == null) {
			return jwtProvider.createToken(member.getLoginId(), false);
		} else {
			return jwtProvider.createToken(member.getLoginId(), true);

		}
	}

	public JwtResponse reissue(ReissueRequest request) {
		Member member = memberRepository.findByLoginId(jwtProvider.parseClaims(request.refreshToken()).getSubject())
			.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

		return jwtProvider.createToken2(member.getLoginId());
	}

	@Transactional
	public Boolean delete(Member member) {
		try {
			memberRepository.delete(member);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
