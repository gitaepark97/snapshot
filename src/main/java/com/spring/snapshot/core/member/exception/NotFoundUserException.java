package com.spring.snapshot.core.member.exception;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends ApplicationException {

    public NotFoundUserException() {
        super(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다.");
    }

}
