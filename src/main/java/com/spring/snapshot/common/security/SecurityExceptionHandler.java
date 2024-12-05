package com.spring.snapshot.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.snapshot.common.exception.ApplicationException;
import com.spring.snapshot.common.exception.ErrorCode;
import com.spring.snapshot.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

final class SecurityExceptionHandler {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    static Customizer<ExceptionHandlingConfigurer<HttpSecurity>> getConfigurer() {
        return config -> config.authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler());
    }

    private static AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, exception) ->
            handleException(response, ErrorCode.UNAUTHORIZED);
    }

    private static AccessDeniedHandler accessDeniedHandler() {
        return (request, response, exception) ->
            handleException(response, ErrorCode.FORBIDDEN);
    }

    private static void handleException(
        HttpServletResponse response,
        ErrorCode errorCode
    ) throws IOException {
        ApplicationException appException = new ApplicationException(errorCode);
        ApiResponse<?> apiResponse = ApiResponse.from(appException);

        writeResponse(response, apiResponse);
    }

    private static void writeResponse(HttpServletResponse response, ApiResponse<?> apiResponse) throws IOException {
        response.setStatus(apiResponse.status().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

}
