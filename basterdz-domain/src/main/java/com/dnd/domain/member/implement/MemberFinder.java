package com.dnd.domain.member.implement;

import static com.dnd.common.exception.ErrorCode.MEMBER_NOT_FOUND;

import java.util.Optional;

import com.dnd.common.exception.NotFoundException;

import com.dnd.domain.common.annotation.Finder;

import com.dnd.domain.member.entity.Member;
import com.dnd.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberJpaRepository memberJpaRepository;

	public Member find(Long id) {
		return memberJpaRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(MEMBER_NOT_FOUND));
	}

	public Optional<Member> findByOauthId(String oauthId) {
		return memberJpaRepository.findByOauthId(oauthId);
	}
}
