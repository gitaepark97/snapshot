package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.application.UserRepository;
import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    @Override
    public boolean existsUserByNickname(String nickname) {
        return userEntityRepository.existsByNickname(nickname);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userEntityRepository.findById(id).map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findUserBySocial(Social social) {
        return userEntityRepository.findBySocialProviderAndSocialId(social.provider(), social.id())
            .map(UserEntity::toUser);
    }

    @Override
    public User saveUser(User user) {
        return userEntityRepository.save(UserEntity.from(user)).toUser();
    }

}
