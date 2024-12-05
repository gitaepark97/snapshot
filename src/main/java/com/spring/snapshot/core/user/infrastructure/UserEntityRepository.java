package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.domain.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNickname(String nickname);

    Optional<UserEntity> findBySocialProviderAndSocialId(SocialProvider socialProvider, String socialId);

}
