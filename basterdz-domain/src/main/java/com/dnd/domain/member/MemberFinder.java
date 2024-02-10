package com.dnd.domain.member;

import static com.dnd.common.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.dnd.common.exception.NotFoundException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberJpaRepository memberJpaRepository;

	public Member find(Long id) {
		return memberJpaRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(MEMBER_NOT_FOUND));
	}
}
