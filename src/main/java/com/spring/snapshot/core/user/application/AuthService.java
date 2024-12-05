package com.spring.snapshot.core.user.application;

import com.spring.snapshot.core.user.domain.Session;
import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserReader userReader;
    private final UserProcessor userProcessor;
    private final TokenProcessor tokenProcessor;

    public Token login(Social social, String nickname, String imageUrl) {
        Session session = userProcessor.login(social, nickname, imageUrl);
        return tokenProcessor.issueToken(session);
    }

    public Token reissueToken(String refreshToken) {
        String sessionId = tokenProcessor.extractSessionId(refreshToken);
        Session session = userReader.getSession(sessionId);
        return tokenProcessor.issueToken(session);
    }

}
