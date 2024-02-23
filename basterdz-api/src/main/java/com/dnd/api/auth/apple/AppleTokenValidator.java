package com.dnd.api.auth.apple;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppleTokenValidator {

	private static final String NONCE_KEY = "nonce";
	private static final String ENCRYPT_ALGORITHM = "SHA-256";
	private static final String FORMAT_CODE = "%02x";

	private final AppleProperties appleProperties;

	public String getSubject(String token, PublicKey publicKey) {
		Claims claims = Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(token).getPayload();
		validClaims(claims);
		return claims.getSubject();
	}

	public boolean validClaims(Claims claims) {
		return claims.getIssuer().contains(appleProperties.getIss()) &&
			claims.getAudience().equals(appleProperties.getClientId()) &&
			claims.get(NONCE_KEY, String.class).equals(encrypt(appleProperties.getNonce()));
	}

	public String encrypt(String value) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
			byte[] digest = sha256.digest(value.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				hexString.append(String.format(FORMAT_CODE, b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Apple OAuth 통신 암호화 과정 중 문제가 발생했습니다.");
		}
	}
}
