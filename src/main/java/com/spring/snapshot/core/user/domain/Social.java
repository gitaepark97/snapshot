package com.spring.snapshot.core.user.domain;

public record Social(
    SocialProvider provider,
    String id
) {

    public static Social of(SocialProvider provider, String id) {
        return new Social(provider, id);
    }

}
