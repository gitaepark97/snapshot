package com.spring.snapshot.common.security;

import com.spring.snapshot.common.provider.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
class TokenFilter extends OncePerRequestFilter {

    private static final String bearer = "Bearer ";

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        Optional<String> token = extractToken(request);
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Map<String, Object> claims = tokenProvider.parse(token.get());
            Long memberId = ((Integer) claims.get("memberId")).longValue();

            Authentication authentication = new UsernamePasswordAuthenticationToken(memberId, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authorization) || !authorization.startsWith(bearer)) {
            return Optional.empty();
        }
        return Optional.of(authorization.substring(bearer.length()));
    }

}
