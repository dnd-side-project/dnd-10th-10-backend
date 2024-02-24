package com.dnd.api.domains.booster;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.api.auth.LoginMember;
import com.dnd.api.common.dto.ApiResult;
import com.dnd.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/boosters")
@RequiredArgsConstructor
public class BoosterController {

	private final BoosterService boosterService;

	@PostMapping("/notification")
	public ApiResult<Void> sendBooster(final @LoginMember Member member, final @RequestBody SendBoosterRequest sendBoosterRequest) {
		boosterService.sendPushMessage(sendBoosterRequest.getTargetId(), sendBoosterRequest.getContent());
		return ApiResult.ok(null);
	}
}
