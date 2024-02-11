package com.dnd.domain.room.implement;

import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.dnd.common.exception.ErrorCode.MEMBER_ALREADY_ENTERED;

@Finder
@RequiredArgsConstructor
public class RoomMemberFinder {

    private final RoomMemberJpaRepository roomMemberJpaRepository;

    public void existsMemberByMemberId(final Member member) {
        Optional<Member> memberOptional =
                roomMemberJpaRepository.existsMemberByMemberId(member.getId());
        if (memberOptional.isPresent()) {
            throw new BadRequestException(MEMBER_ALREADY_ENTERED);
        }
    }
}
