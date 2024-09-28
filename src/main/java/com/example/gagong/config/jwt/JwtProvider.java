package com.example.gagong.config.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.gagong.config.CustomUserDetailsService;
import com.example.gagong.dto.response.JwtResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private final Key key;

	private final CustomUserDetailsService customUserDetailsService;

	public JwtProvider(@Value("${jwt.secret}") String secretKey,
		CustomUserDetailsService CustomUserDetailsService) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.customUserDetailsService = CustomUserDetailsService;
	}

	public JwtResponse createToken(String loginId, boolean isInviteCode) {
		Date now = new Date();
		long accessTokenExpired = 30 * 60 * 1000L;  //30분
		long refreshTokenExpired = 7L * 24 * 60 * 60 * 1000; //7일

		Date accessTokenExpiredTime = new Date(now.getTime() + accessTokenExpired);
		Date refreshTokenExpiredTime = new Date(now.getTime() + refreshTokenExpired);
		Claims claims = Jwts.claims().setSubject(loginId);

		String accessToken = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(accessTokenExpiredTime)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		String refreshToken = Jwts.builder()
			.setClaims(claims)
			.setExpiration(refreshTokenExpiredTime)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return JwtResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.isInviteCode(isInviteCode)
			.build();
	}

	public JwtResponse createToken2(String loginId) {
		Date now = new Date();
		long accessTokenExpired = 30 * 60 * 1000L;  //30분
		long refreshTokenExpired = 7L * 24 * 60 * 60 * 1000; //7일

		Date accessTokenExpiredTime = new Date(now.getTime() + accessTokenExpired);
		Date refreshTokenExpiredTime = new Date(now.getTime() + refreshTokenExpired);
		Claims claims = Jwts.claims().setSubject(loginId);

		String accessToken = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(accessTokenExpiredTime)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		String refreshToken = Jwts.builder()
			.setClaims(claims)
			.setExpiration(refreshTokenExpiredTime)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return JwtResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.isInviteCode(true)
			.build();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Authentication getAuthentication(String accessToken) {
		Claims claims = parseClaims(accessToken);

		UserDetails principal = customUserDetailsService.loadUserByUsername(claims.getSubject());
		return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
	}

	public Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
