package com.spring.snapshot.core.member.web;

import com.spring.snapshot.core.member.domain.Member;

record MyMemberResponse(
    Long id,
    String email,
    String nickname,
    String imageUrl
) {

    static MyMemberResponse from(Member member) {
        return new MyMemberResponse(
            member.id(),
            member.email(),
            member.nickname(),
            member.imageUrl()
        );
    }

}

record MemberResponse(
    Long id,
    String nickname,
    String imageUrl
) {

    static MemberResponse from(Member member) {
        return new MemberResponse(
            member.id(),
            member.nickname(),
            member.imageUrl()
        );
    }

}
