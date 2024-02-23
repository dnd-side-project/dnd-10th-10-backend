package com.dnd.api.auth.apple;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ApplePublicKeyGenerator {
	private static final String ALGORITHM_HEADER_KEY = "alg";
	private static final String KEY_ID_HEADER_KEY = "kid";
	private static final int SIGNUM = 1;

	public PublicKey generatePublicKey(Map<String, String> headers, ApplePublicKeys applePublicKeys) {
		ApplePublicKey applePublicKey =
			applePublicKeys.getMatchesKey(headers.get(ALGORITHM_HEADER_KEY), headers.get(KEY_ID_HEADER_KEY));

		return generatePublicKeyWithApplePublicKey(applePublicKey);
	}

	private PublicKey generatePublicKeyWithApplePublicKey(ApplePublicKey publicKey) {
		byte[] nBytes = Base64.getUrlDecoder().decode(publicKey.getN());
		byte[] eBytes = Base64.getUrlDecoder().decode(publicKey.getE());

		BigInteger n = new BigInteger(SIGNUM, nBytes);
		BigInteger e = new BigInteger(SIGNUM, eBytes);

		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
			return keyFactory.generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			throw new IllegalStateException("Apple OAuth 로그인 중 public key 생성에 문제가 발생했습니다.");
		}
	}

}
