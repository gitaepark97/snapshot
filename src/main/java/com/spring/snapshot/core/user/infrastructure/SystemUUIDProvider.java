package com.spring.snapshot.core.user.infrastructure;

import com.spring.snapshot.core.user.application.UUIDProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class SystemUUIDProvider implements UUIDProvider {

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
