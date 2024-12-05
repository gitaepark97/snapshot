package com.spring.snapshot.core.user.domain;

public record Token(
    String accessToken,
    String refreshToken
) {

    public static Token of(String accessToken, String refreshToken) {
        return new Token(accessToken, refreshToken);
    }

}
