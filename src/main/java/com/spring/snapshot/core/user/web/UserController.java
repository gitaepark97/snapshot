package com.spring.snapshot.core.user.web;

import com.spring.snapshot.core.user.application.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService;

    @PostMapping("/logout")
    void logout(@AuthenticationPrincipal Long userId) {
        userService.logout(userId);
    }

    @GetMapping("/my")
    MyUserResponse getMyUser(@AuthenticationPrincipal Long userId) {
        return MyUserResponse.from(userService.getUser(userId));
    }

    @PutMapping("/my")
    MyUserResponse updateMyUser(@AuthenticationPrincipal Long userId, @RequestBody @Valid UpdateUserRequest request) {
        return MyUserResponse.from(userService.updateUser(userId, request.nickname(), request.imageUrl()));
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable Long userId) {
        return UserResponse.from(userService.getUser(userId));
    }

}
