package com.spring.snapshot.core.member.web;

import com.spring.snapshot.core.member.application.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private final MemberService memberService;

    @PostMapping("/register")
    void register(@RequestBody @Valid RegisterRequest request) {
        memberService.register(request.email(), request.password(), request.nickname(), request.imageUrl());
    }

    @PostMapping("/login")
    TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return TokenResponse.from(memberService.login(request.email(), request.password()));
    }

}
