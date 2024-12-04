package com.spring.snapshot.common.response;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
    HttpStatus status,
    String message,
    T data
) {

    static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return switch (status) {
            case HttpStatus.OK, HttpStatus.CREATED -> new ApiResponse<>(status, "성공", data);
            default -> new ApiResponse<>(status, "실패", data);
        };
    }

    public static <T> ApiResponse<T> from(ApplicationException e) {
        return new ApiResponse<>(e.getStatus(), e.getMessage(), null);
    }

}
