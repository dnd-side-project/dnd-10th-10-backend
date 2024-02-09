package com.dnd.api.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dnd.common.exception.ErrorCode;
import com.dnd.common.exception.UnauthorizedException;
import com.dnd.domain.member.Member;
import com.dnd.domain.member.MemberFinder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String LOGIN_ID = "Login-Id";

	private final MemberFinder memberFinder;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginMember.class)
			&& parameter.getParameterType() == Member.class;

	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.info(webRequest.getContextPath());
		String value = webRequest.getHeader(LOGIN_ID);
		if(value == null) throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		Long loginId = Long.valueOf(value);
		return memberFinder.find(loginId);
	}
}
