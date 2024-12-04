package com.spring.snapshot.core.member.application;

import com.spring.snapshot.core.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);
    
    Member save(Member member);

}
