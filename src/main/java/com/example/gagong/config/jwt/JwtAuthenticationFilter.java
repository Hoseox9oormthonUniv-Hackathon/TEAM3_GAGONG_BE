package com.example.gagong.config.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final JwtProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		try {
			String token = resolveToken((HttpServletRequest)servletRequest);

			if (jwtTokenProvider.validateToken(token)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			servletRequest.setAttribute("exception", e);
		} finally {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	private String resolveToken(HttpServletRequest servletRequest) {
		String token = servletRequest.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer "))
			throw new IllegalArgumentException("토큰이 존재하지 않습니다.");

		return token.substring(7);
	}
}
