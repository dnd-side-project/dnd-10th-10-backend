package com.dnd.api.auth.dto;

import com.dnd.domain.member.entity.OauthMemberInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppleLoginReqeust {

	private String identityToken;

	private String email;

	private String name;

	public OauthMemberInfo toOauthMemberInfo() {
		return OauthMemberInfo.of(email, name);
	}
}
