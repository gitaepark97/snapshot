package com.spring.snapshot.common.provider.impl;

import com.spring.snapshot.common.provider.EncoderProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class BCryptEncoderProvider implements EncoderProvider {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String data) {
        return passwordEncoder.encode(data);
    }

    @Override
    public boolean matches(String data, String encoded) {
        return passwordEncoder.matches(data, encoded);
    }

}
