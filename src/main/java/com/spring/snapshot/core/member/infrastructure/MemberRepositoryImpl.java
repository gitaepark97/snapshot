package com.spring.snapshot.core.member.infrastructure;

import com.spring.snapshot.core.member.application.MemberRepository;
import com.spring.snapshot.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class MemberRepositoryImpl implements MemberRepository {

    private final MemberEntityRepository memberEntityRepository;

    @Override
    public boolean existsByEmail(String email) {
        return memberEntityRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return memberEntityRepository.existsByNickname(nickname);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberEntityRepository.findById(id).map(MemberEntity::toMember);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberEntityRepository.findByEmail(email).map(MemberEntity::toMember);
    }

    @Override
    public Member save(Member member) {
        return memberEntityRepository.save(MemberEntity.from(member)).toMember();
    }

}
