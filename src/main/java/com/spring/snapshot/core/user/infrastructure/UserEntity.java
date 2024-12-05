package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.SocialProvider;
import com.spring.snapshot.core.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"user\"")
@DynamicInsert
@DynamicUpdate
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    SocialProvider socialProvider;

    String socialId;

    String nickname;

    String imageUrl;

    Long lastLoginTime;

    Long createTime;

    Long updateTime;

    static UserEntity from(User user) {
        return new UserEntity(
            user.id(),
            user.social().provider(),
            user.social().id(),
            user.nickname(),
            user.imageUrl(),
            user.lastLoginTime(),
            user.createTime(),
            user.updateTime()
        );
    }

    User toUser() {
        return User.builder()
            .id(id)
            .social(Social.of(socialProvider, socialId))
            .nickname(nickname)
            .imageUrl(imageUrl)
            .lastLoginTime(lastLoginTime)
            .createTime(createTime)
            .updateTime(updateTime)
            .build();
    }

}
