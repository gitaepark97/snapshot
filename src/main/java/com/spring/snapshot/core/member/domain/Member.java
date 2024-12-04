package com.spring.snapshot.core.member.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Member(
    Long id,
    String email,
    String password,
    String nickname,
    Long lastLoginAt,
    String imageUrl,
    Long createdAt,
    Long updatedAt
) {

    public static Member of(
        String email,
        String password,
        String nickname,
        String imageUrl,
        Long currentTime
    ) {
        return Member.builder()
            .email(email)
            .password(password)
            .nickname(nickname)
            .imageUrl(imageUrl)
            .createdAt(currentTime)
            .updatedAt(currentTime)
            .build();
    }

    public Member login(Long currentTime) {
        return toBuilder()
            .lastLoginAt(currentTime)
            .build();
    }

    public boolean isNicknameChanged(String nickname) {
        return !this.nickname.equals(nickname);
    }

    public Member update(String nickname, String imageUrl) {
        return toBuilder()
            .nickname(nickname)
            .imageUrl(imageUrl)
            .build();
    }

}
