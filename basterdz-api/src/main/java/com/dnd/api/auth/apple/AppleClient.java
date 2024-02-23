package com.dnd.api.auth.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "applePublicKeyClient", url = "https://appleid.apple.com")
public interface AppleClient {

	@GetMapping("/auth/keys")
	ApplePublicKeys getApplePublicKeys();
}
