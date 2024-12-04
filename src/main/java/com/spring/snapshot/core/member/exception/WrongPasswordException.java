package com.spring.snapshot.core.member.exception;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends ApplicationException {

    public WrongPasswordException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");
    }

}
