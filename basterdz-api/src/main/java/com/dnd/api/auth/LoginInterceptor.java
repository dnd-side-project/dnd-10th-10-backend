package com.dnd.api.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (isPreflight(request)) {
			return true;
		}
		String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
		String token = getAccessToken(authorizationHeader);
		String memberId = String.valueOf(jwtTokenProvider.getClaims(token).get("memberId"));
		request.setAttribute("loginId", memberId);
		return true;
	}

	private boolean isPreflight(HttpServletRequest request) {
		return request.getMethod().equals("OPTIONS");
	}

	private String getAccessToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
			return authorizationHeader.substring(TOKEN_PREFIX.length());
		}
		return null;
	}
}
