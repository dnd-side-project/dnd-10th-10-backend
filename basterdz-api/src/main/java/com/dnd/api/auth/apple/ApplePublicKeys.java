package com.dnd.api.auth.apple;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplePublicKeys {

	private List<ApplePublicKey> keys;

	public ApplePublicKey getMatchesKey(String kid, String alg) {
		return this.keys
			.stream()
			.filter(k -> k.getKid().equals(kid) && k.getAlg().equals(alg))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Apple JWT 값의 alg, kid 정보가 올바르지 않습니다."));
	}
}
