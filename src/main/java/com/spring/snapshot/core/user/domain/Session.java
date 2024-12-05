package com.spring.snapshot.core.user.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Session(
    String id,
    Long userId,
    Long createTime
) {

    static Session of(String id, Long userId, Long currentTime) {
        return Session.builder()
            .id(id)
            .userId(userId)
            .createTime(currentTime)
            .build();
    }

    public boolean isSessionUser(Long userId) {
        return this.userId.equals(userId);
    }

}
