package com.dnd.domain.member.implement;

import com.dnd.domain.common.annotation.Remover;
import com.dnd.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

@Remover
@RequiredArgsConstructor
public class MemberRemover {

    private final MemberJpaRepository memberJpaRepository;

    public void deleteAll() {
        memberJpaRepository.deleteAllInBatch();
    }
}
