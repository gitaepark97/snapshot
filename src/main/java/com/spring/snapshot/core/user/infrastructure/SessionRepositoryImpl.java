package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.application.SessionRepository;
import com.spring.snapshot.core.user.domain.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class SessionRepositoryImpl implements SessionRepository {

    private final SessionEntityRepository sessionEntityRepository;

    @Override
    public Optional<Session> findSessionById(String id) {
        return sessionEntityRepository.findById(id).map(SessionEntity::toSession);
    }

    @Override
    public Session saveSession(Session session) {
        return sessionEntityRepository.save(SessionEntity.from(session)).toSession();
    }

    @Override
    public void deleteSessionByUserId(Long userId) {
        sessionEntityRepository.deleteByUserId(userId);
    }

}
