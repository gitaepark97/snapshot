package com.spring.snapshot.core.user.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record User(
    Long id,
    Social social,
    String nickname,
    String imageUrl,
    Long lastLoginTime,
    Long createTime,
    Long updateTime
) {

    public static User of(
        Social social,
        String nickname,
        String imageUrl,
        Long currentTime
    ) {
        return User.builder()
            .social(social)
            .nickname(nickname)
            .imageUrl(imageUrl)
            .createTime(currentTime)
            .updateTime(currentTime)
            .build();
    }

    public User login(Long currentTime) {
        return toBuilder()
            .lastLoginTime(currentTime)
            .build();
    }

    public Session createSession(String id, Long currentTime) {
        return Session.of(id, this.id, currentTime);
    }

    public boolean isSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    public User update(String nickname, String imageUrl, Long currentTime) {
        return toBuilder()
            .nickname(nickname)
            .imageUrl(imageUrl)
            .updateTime(currentTime)
            .build();
    }

}
