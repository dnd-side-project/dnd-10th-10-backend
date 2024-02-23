package com.dnd.api.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.api.auth.dto.AppleLoginReqeust;
import com.dnd.api.auth.dto.LoginResponse;
import com.dnd.api.common.dto.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/apple")
	public ApiResult<LoginResponse> appleLogin(
		final @RequestBody AppleLoginReqeust appleLoginReqeust
	) {
		LoginResponse loginResponse = authService.login(appleLoginReqeust.toOauthMemberInfo(), appleLoginReqeust.getIdentityToken());
		return ApiResult.ok(loginResponse);
	}
}
