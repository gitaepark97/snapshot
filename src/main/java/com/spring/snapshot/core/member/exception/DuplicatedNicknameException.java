package com.spring.snapshot.core.member.exception;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class DuplicatedNicknameException extends ApplicationException {

    public DuplicatedNicknameException() {
        super(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다.");
    }

}
