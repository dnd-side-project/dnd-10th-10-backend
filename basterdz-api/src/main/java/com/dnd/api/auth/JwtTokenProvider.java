package com.dnd.api.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

	private final String issuer;
	private final int expirySeconds;
	private final SecretKey secretKey;

	public JwtTokenProvider(JwtTokenProperties jwtProperties) {
		this.issuer = jwtProperties.getIssuer();
		this.expirySeconds = jwtProperties.getExpirySeconds();
		this.secretKey = new SecretKeySpec(
			jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
			Jwts.SIG.HS256.key().build().getAlgorithm()
		);;
	}

	public String createAccessToken(Long memberId) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirySeconds*100000);
		return Jwts.builder()
			.issuer(issuer)
			.issuedAt(now)
			.expiration(expiry)
			.claim("memberId", memberId)
			.signWith(secretKey)
			.compact();
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
		} catch (ExpiredJwtException e) {
			throw new IllegalArgumentException();
		} catch (JwtException e) {
			throw new JwtException("no");
		}
	}
}
