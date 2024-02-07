package com.dnd.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dnd.common.error.ErrorCode;
import com.dnd.common.error.exception.UnauthorizedException;
import com.dnd.member.Member;
import com.dnd.member.MemberFinder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String LOGIN_ID = "LOGIN_ID";

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
		String a = webRequest.getHeader(LOGIN_ID);
		log.info("ssss + {}" , a);
		if(a == null) throw new UnauthorizedException(ErrorCode.METHOD_ARGUMENT_NOT_VALID);
		Long loginMemberId = Long.valueOf(a);
		System.out.println(loginMemberId);
		return memberFinder.find(loginMemberId);
	}
}
