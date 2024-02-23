package com.dnd.domain.member.implement;

import com.dnd.domain.common.annotation.Appender;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

@Appender
@RequiredArgsConstructor
public class MemberAppender {

    private final MemberJpaRepository memberJpaRepository;

    public Member append(Member member) {
        return memberJpaRepository.save(member);
    }
}
