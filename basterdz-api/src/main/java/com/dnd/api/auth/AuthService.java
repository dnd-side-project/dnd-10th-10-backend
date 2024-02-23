package com.dnd.api.auth;

import java.security.PublicKey;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dnd.api.auth.apple.AppleClient;
import com.dnd.api.auth.apple.ApplePublicKeyGenerator;
import com.dnd.api.auth.apple.AppleTokenParser;
import com.dnd.api.auth.apple.AppleTokenValidator;
import com.dnd.api.auth.dto.LoginResponse;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.member.entity.OauthMemberInfo;
import com.dnd.domain.member.implement.MemberAppender;
import com.dnd.domain.member.implement.MemberFinder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthService {

	private final AppleClient appleClient;
	private final ApplePublicKeyGenerator applePublicKeyGenerator;
	private final AppleTokenParser appleTokenParser;
	private final AppleTokenValidator appleTokenValidator;
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberFinder memberFinder;
	private final MemberAppender memberAppender;

	public LoginResponse login(OauthMemberInfo oauthMemberInfo, String identifyToken) {
		String clientId = getAppleClientId(identifyToken);
		Optional<Member> memberByOauthId = memberFinder.findByOauthId(clientId);
		if(memberByOauthId.isEmpty()) {
			Member member = saveIfNotExist(oauthMemberInfo, clientId);
			return LoginResponse.from(generateAccessToken(member));
		}
		return LoginResponse.from(generateAccessToken(memberByOauthId.get()));
	}

	public String getAppleClientId(String identifyToken) {
		Map<String, String> headers = appleTokenParser.parseHeaders(identifyToken);
		PublicKey publicKey = applePublicKeyGenerator.generatePublicKey(headers, appleClient.getApplePublicKeys());
		return appleTokenValidator.getSubject(identifyToken, publicKey);
	}

	public Member saveIfNotExist(OauthMemberInfo oauthMemberInfo, String clientId) {
		return memberAppender.append(Member.create(oauthMemberInfo, clientId));
	}

	public String generateAccessToken(Member member) {
		return jwtTokenProvider.createAccessToken(member.getId());
	}

}
