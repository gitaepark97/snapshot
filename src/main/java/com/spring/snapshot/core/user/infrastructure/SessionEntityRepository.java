package com.spring.snapshot.core.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface SessionEntityRepository extends JpaRepository<SessionEntity, String> {

    void deleteByUserId(Long userId);

}
