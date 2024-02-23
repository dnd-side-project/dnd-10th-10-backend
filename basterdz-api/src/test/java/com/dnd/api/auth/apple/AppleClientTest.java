package com.dnd.api.auth.apple;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppleClientTest {

	@Autowired
	private AppleClient appleClient;

	@Test
	void getPublicKeys() {
		ApplePublicKeys applePublicKeys = appleClient.getApplePublicKeys();
		List<ApplePublicKey> keys = applePublicKeys.getKeys();

		keys.forEach(key -> {
			assertThat(key.getKid()).isNotNull();
			assertThat(key.getAlg()).isNotNull();
			assertThat(key.getKid()).isNotNull();
			assertThat(key.getN()).isNotNull();
			assertThat(key.getE()).isNotNull();
		});
	}
}
