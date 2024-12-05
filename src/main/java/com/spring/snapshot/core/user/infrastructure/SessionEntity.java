package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.domain.Session;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "\"session\"")
@DynamicUpdate
class SessionEntity {

    @Id
    String id;

    @Column(nullable = false)
    Long userId;

    Long createTime;

    static SessionEntity from(Session session) {
        return new SessionEntity(
            session.id(),
            session.userId(),
            session.createTime()
        );
    }

    Session toSession() {
        return Session.builder()
            .id(id)
            .userId(userId)
            .createTime(createTime)
            .build();
    }

}
