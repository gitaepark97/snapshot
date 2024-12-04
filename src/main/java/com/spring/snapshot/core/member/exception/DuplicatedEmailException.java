package com.spring.snapshot.core.member.exception;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends ApplicationException {

    public DuplicatedEmailException() {
        super(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");
    }

}
