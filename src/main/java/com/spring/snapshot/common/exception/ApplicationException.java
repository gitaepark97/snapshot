package com.spring.snapshot.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ApplicationException(ErrorCode code) {
        super(code.getMessage());
        this.status = code.getStatus();
    }

}
