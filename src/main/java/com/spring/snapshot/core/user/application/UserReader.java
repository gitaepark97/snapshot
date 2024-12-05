package com.spring.snapshot.core.user.application;

import com.spring.snapshot.common.exception.ApplicationException;
import com.spring.snapshot.common.exception.ErrorCode;
import com.spring.snapshot.core.user.domain.Session;
import com.spring.snapshot.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserReader {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public User getUser(Long userId) {
        return userRepository.findUserById(userId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    Session getSession(String sessionId) {
        return sessionRepository.findSessionById(sessionId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.UNAUTHORIZED));
    }

}
