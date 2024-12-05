package com.spring.snapshot.core.user.application;

import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    boolean existsUserByNickname(String nickname);

    Optional<User> findUserById(Long id);

    Optional<User> findUserBySocial(Social social);

    User saveUser(User user);

}
