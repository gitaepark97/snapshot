package com.spring.snapshot.core.user.application;

import com.spring.snapshot.core.user.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findSessionById(String id);

    Session saveSession(Session session);

    void deleteSessionByUserId(Long userId);

}
