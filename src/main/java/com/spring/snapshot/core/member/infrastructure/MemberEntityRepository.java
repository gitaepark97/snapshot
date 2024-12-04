package com.spring.snapshot.core.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<MemberEntity> findByEmail(String email);

}
