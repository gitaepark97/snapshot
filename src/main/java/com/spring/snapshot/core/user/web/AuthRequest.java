package com.spring.snapshot.core.user.web;

import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.SocialProvider;

record LoginRequest(
    SocialProvider socialProvider,
    String socialId,
    String nickname,
    String imageUrl
) {

    Social toSocial() {
        return Social.of(socialProvider, socialId);
    }

}
