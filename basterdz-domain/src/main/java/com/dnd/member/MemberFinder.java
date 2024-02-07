package com.dnd.member;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberJpaRepository memberJpaRepository;

	public Member find(Long id) {
		return memberJpaRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}
}
