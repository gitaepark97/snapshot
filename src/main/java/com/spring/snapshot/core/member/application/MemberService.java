package com.spring.snapshot.core.member.application;

import com.spring.snapshot.core.member.domain.Member;

public interface MemberService {

    Member getMember(Long memberId);

    void register(String email, String password, String nickname, String imageUrl);

    String login(String email, String password);

    Member updateMember(Long memberId, String nickname, String imageUrl);

}
