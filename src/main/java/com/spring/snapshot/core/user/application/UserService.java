package com.spring.snapshot.core.user.application;

import com.spring.snapshot.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserReader userReader;
    private final UserProcessor userProcessor;

    public User getUser(Long userId) {
        return userReader.getUser(userId);
    }

    public void logout(Long userId) {
        userProcessor.logout(userId);
    }

    public User updateUser(Long userId, String nickname, String imageUrl) {
        return userProcessor.updateUser(userId, nickname, imageUrl);
    }

}
