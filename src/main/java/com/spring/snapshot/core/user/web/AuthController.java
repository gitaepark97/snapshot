package com.spring.snapshot.core.user.web;

import com.spring.snapshot.core.user.application.AuthService;
import com.spring.snapshot.core.user.domain.Token;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        Token token = authService.login(request.toSocial(), request.nickname(), request.imageUrl());
        return createResponseWithCookie(token);

    }

    @PostMapping("/reissue-token")
    ResponseEntity<TokenResponse> reissueToken(@CookieValue("refreshToken") String refreshToken) {
        Token token = authService.reissueToken(refreshToken);
        return createResponseWithCookie(token);
    }

    private ResponseEntity<TokenResponse> createResponseWithCookie(Token token) {
        String cookie = createRefreshTokenCookie(token.refreshToken());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie)
            .body(TokenResponse.from(token));
    }

    private String createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(AuthController.REFRESH_TOKEN_COOKIE_NAME, refreshToken)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .build()
            .toString();
    }

}
