package com.spring.snapshot.core.user.application;


import com.spring.snapshot.common.exception.ApplicationException;
import com.spring.snapshot.common.exception.ErrorCode;
import com.spring.snapshot.common.infrastructure.ClockProvider;
import com.spring.snapshot.core.user.domain.Session;
import com.spring.snapshot.core.user.domain.Social;
import com.spring.snapshot.core.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class UserProcessor {

    private static final int MAX_TRY_COUNT = 5;

    private final ClockProvider clockProvider;
    private final UUIDProvider uuidProvider;
    private final RandomProvider randomProvider;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    Session login(Social social, String nickname, String imageUrl) {
        User user = userRepository.findUserBySocial(social)
            .orElseGet(() -> createUser(social, nickname, imageUrl))
            .login(clockProvider.millis());
        userRepository.saveUser(user);
        return createSession(user);
    }

    @Transactional
    void logout(Long userId) {
        sessionRepository.deleteSessionByUserId(userId);
    }

    User updateUser(Long userId, String nickname, String imageUrl) {
        User existUser = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));

        if (!existUser.isSameNickname(nickname)) {
            checkDuplicatedNickname(nickname);
        }

        User updatedUser = existUser.update(nickname, imageUrl, clockProvider.millis());
        return userRepository.saveUser(updatedUser);
    }

    private void checkDuplicatedNickname(String nickname) {
        if (userRepository.existsUserByNickname(nickname)) {
            throw new ApplicationException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    private Session createSession(User user) {
        sessionRepository.deleteSessionByUserId(user.id());
        Session newSession = user.createSession(uuidProvider.generateUUID(), clockProvider.millis());
        return sessionRepository.saveSession(newSession);
    }

    private User createUser(Social social, String nickname, String imageUrl) {
        String nicknameWithRandomNumber = generateNicknameWithRandomNumber(nickname);
        User newUser = User.of(social, nicknameWithRandomNumber, imageUrl, clockProvider.millis());
        return userRepository.saveUser(newUser);
    }

    private String generateNicknameWithRandomNumber(String nickname) {
        int tryCount = 0;
        while (tryCount < MAX_TRY_COUNT) {
            String nicknameWithRandomNumber = nickname + generateRandomDigitCode();
            if (!userRepository.existsUserByNickname(nicknameWithRandomNumber)) {
                return nicknameWithRandomNumber;
            }
            tryCount += 1;
        }

        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private String generateRandomDigitCode() {
        int randomNumber = randomProvider.randomInt(0, 9999);
        return String.format("%04d", randomNumber);
    }

}
