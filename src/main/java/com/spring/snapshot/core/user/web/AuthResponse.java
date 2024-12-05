package com.spring.snapshot.core.user.web;

import com.spring.snapshot.core.user.domain.Token;

record TokenResponse(
    String accessToken
) {

    static TokenResponse from(Token token) {
        return new TokenResponse(token.accessToken());
    }

}
