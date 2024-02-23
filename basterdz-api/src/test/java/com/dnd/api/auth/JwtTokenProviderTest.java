package com.dnd.api.auth;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;

@SpringBootTest
class JwtTokenProviderTest {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Test
	void createAccessToken() {
		Long memberId = 1L;
		String accessToken = jwtTokenProvider.createAccessToken(memberId);
		System.out.println(accessToken);
	}

	@Test
	void getClaims() {
		Long memberId = 2L;
		String accessToken = jwtTokenProvider.createAccessToken(memberId);
		Claims claims = jwtTokenProvider.getClaims(accessToken);
		System.out.println(claims.getIssuedAt());
		System.out.println(claims.getExpiration());
		assertThat(claims.get("memberId"));
	}
}
