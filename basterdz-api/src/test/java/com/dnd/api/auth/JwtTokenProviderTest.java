package com.dnd.api.auth;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenProviderTest {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Test
	void createAccessToken() {
		Long memberId = 1L;
		String accessToken = jwtTokenProvider.createAccessToken(memberId);
		Long memberId2 = 2L;
		String accessToken2 = jwtTokenProvider.createAccessToken(memberId);
		Long memberId3 = 3L;
		String accessToken3 = jwtTokenProvider.createAccessToken(memberId);
		Long memberId4 = 4L;
		String accessToken4 = jwtTokenProvider.createAccessToken(memberId);
		Long memberId5 = 5L;
		String accessToken5 = jwtTokenProvider.createAccessToken(memberId);
		Long memberId6 = 6L;
		String accessToken6 = jwtTokenProvider.createAccessToken(memberId);
		System.out.println("1: " + accessToken);
		System.out.println("2: " + accessToken2);
		System.out.println("3: " + accessToken3);
		System.out.println("4: " + accessToken4);
		System.out.println("5: " + accessToken5);
		System.out.println("6: " + accessToken6);

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
