package com.dnd.domain.member.repository;

import java.util.Optional;

import com.dnd.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByOauthId(String ouathId);
}
