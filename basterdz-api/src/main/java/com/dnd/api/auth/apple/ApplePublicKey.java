package com.dnd.api.auth.apple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplePublicKey {
	private final String kty;
	private final String kid;
	private final String alg;
	private final String n;
	private final String e;
}
