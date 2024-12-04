package com.spring.snapshot.core.member.application.impl;

import com.spring.snapshot.common.provider.ClockProvider;
import com.spring.snapshot.common.provider.EncoderProvider;
import com.spring.snapshot.common.provider.TokenProvider;
import com.spring.snapshot.core.member.application.MemberRepository;
import com.spring.snapshot.core.member.application.MemberService;
import com.spring.snapshot.core.member.domain.Member;
import com.spring.snapshot.core.member.exception.DuplicatedEmailException;
import com.spring.snapshot.core.member.exception.DuplicatedNicknameException;
import com.spring.snapshot.core.member.exception.NotFoundUserException;
import com.spring.snapshot.core.member.exception.WrongPasswordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
class MemberServiceImpl implements MemberService {

    private final EncoderProvider encoderProvider;
    private final ClockProvider clockProvider;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
    }

    @Transactional
    @Override
    public void register(String email, String password, String nickname, String imageUrl) {
        checkDuplicatedEmail(email);
        checkDuplicatedNickname(nickname);

        Member member = Member.of(email, encoderProvider.encode(password), nickname, imageUrl, clockProvider.millis());
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public String login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(NotFoundUserException::new);

        if (!encoderProvider.matches(password, member.password())) {
            throw new WrongPasswordException();
        }

        member = member.login(clockProvider.millis());
        memberRepository.save(member);

        return tokenProvider.issue(Map.of("memberId", member.id()));
    }

    @Transactional
    @Override
    public Member updateMember(Long memberId, String nickname, String imageUrl) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);

        if (member.isNicknameChanged(nickname)) {
            checkDuplicatedNickname(nickname);
        }
        member = member.update(nickname, imageUrl);
        return memberRepository.save(member);
    }

    private void checkDuplicatedEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedEmailException();
        }
    }

    private void checkDuplicatedNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicatedNicknameException();
        }
    }

}
