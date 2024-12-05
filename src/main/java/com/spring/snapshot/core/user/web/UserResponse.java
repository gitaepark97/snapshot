package com.spring.snapshot.core.user.web;

import com.spring.snapshot.core.user.domain.SocialProvider;
import com.spring.snapshot.core.user.domain.User;

record MyUserResponse(
    Long id,
    SocialProvider socialProvider,
    String nickname,
    String imageUrl
) {

    static MyUserResponse from(User user) {
        return new MyUserResponse(user.id(), user.social().provider(), user.nickname(), user.imageUrl());
    }

}

record UserResponse(
    Long id,
    String nickname,
    String imageUrl
) {

    static UserResponse from(User user) {
        return new UserResponse(user.id(), user.nickname(), user.imageUrl());
    }

}