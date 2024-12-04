package com.spring.snapshot.core.member.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


record RegisterRequest(
    @NotEmpty
    @Email
    @Length(max = 100)
    String email,

    @NotEmpty
    @Length(min = 8)
    String password,

    @NotEmpty
    @Length(max = 30)
    String nickname,

    @NotEmpty
    String imageUrl
) {

}

record LoginRequest(
    @NotEmpty
    String email,

    @NotEmpty
    String password
) {

}

