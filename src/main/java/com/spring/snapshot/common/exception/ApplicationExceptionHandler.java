package com.spring.snapshot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    ApplicationException handleApplicationException(ApplicationException e) {
        return e;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ApplicationException handleNoResourceFoundException(NoResourceFoundException ignored) {
        return new ApplicationException(HttpStatus.NOT_FOUND, "해당 리소스를 찾을 수 없습니다.");
    }

    @ExceptionHandler(Exception.class)
    ApplicationException handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");
    }

}
