package com.dnd.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OauthMemberInfo {

	private String email;

	private String name;

	public static OauthMemberInfo of(String email, String name) {
		return OauthMemberInfo.builder()
			.email(email)
			.name(name)
			.build();
	}
}
