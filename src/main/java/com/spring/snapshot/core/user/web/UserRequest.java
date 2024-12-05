package com.spring.snapshot.core.user.web;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

record UpdateUserRequest(
    @NotEmpty
    @Length(max = 30)
    String nickname,

    @NotEmpty
    String imageUrl
) {

}
