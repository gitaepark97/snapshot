package com.spring.snapshot.core.member.web;

record TokenResponse(
    String accessToken
) {

    static TokenResponse from(String accessToken) {
        return new TokenResponse(accessToken);
    }

}
