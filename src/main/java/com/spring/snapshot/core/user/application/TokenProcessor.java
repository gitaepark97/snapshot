package com.spring.snapshot.core.user.application;

import com.spring.snapshot.common.exception.ApplicationException;
import com.spring.snapshot.common.exception.ErrorCode;
import com.spring.snapshot.common.infrastructure.TokenProvider;
import com.spring.snapshot.core.user.domain.Session;
import com.spring.snapshot.core.user.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Component
class TokenProcessor {

    private static final String USER_ID_KEY = "userId";
    private static final String SESSION_ID_KEY = "sessionId";

    private static final Duration ACCESS_TOKEN_EXPIRATION = Duration.ofHours(1);
    private static final Duration REFRESH_TOKEN_EXPIRATION = Duration.ofDays(7);

    private final TokenProvider tokenProvider;

    Token issueToken(Session session) {

        return Token.of(
            tokenProvider.issue(Map.of(USER_ID_KEY, session.userId()), ACCESS_TOKEN_EXPIRATION),
            tokenProvider.issue(Map.of(SESSION_ID_KEY, session.id()), REFRESH_TOKEN_EXPIRATION)
        );
    }

    String extractSessionId(String refreshToken) {
        try {
            Map<String, Object> claims = tokenProvider.parse(refreshToken);
            return claims.get(SESSION_ID_KEY).toString();
        } catch (Exception ignored) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED);
        }
    }

}
