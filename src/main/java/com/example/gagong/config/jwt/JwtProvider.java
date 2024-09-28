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

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private final Key key;
	private final long ACCESSTOKEN_VALIDTIME = 30 * 60 * 1000L; // 30분
	private final long REFRESHTOKEN_VALIDTIME = 7L * 24 * 60 * 60 * 1000; // 7일

	private final CustomUserDetailsService customUserDetailsService;

	public JwtProvider(@Value("${jwt.secret}") String secretKey,
		CustomUserDetailsService CustomUserDetailsService) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.customUserDetailsService = CustomUserDetailsService;
	}

	public JwtResponse createToken(String loginId) {
		Date now = new Date();
		Date accessTokenExpiredTime = new Date(now.getTime() + ACCESSTOKEN_VALIDTIME);
		Date refreshTokenExpiredTime = new Date(now.getTime() + REFRESHTOKEN_VALIDTIME);
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
			.build();
	}

	public boolean validateToken(String token) throws Exception {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException | SignatureException e) {
			return false;
		} catch (UnsupportedJwtException e) {
			return false;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (ClaimJwtException e) {
			return false;
		}
	}

	public Authentication getAuthentication(String accessToken) throws Exception {
		Claims claims = parseClaims(accessToken);

		UserDetails principal = customUserDetailsService.loadUserByUsername(claims.getSubject());
		return new UsernamePasswordAuthenticationToken(principal, "");
	}

	public Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken)
				.getBody();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
