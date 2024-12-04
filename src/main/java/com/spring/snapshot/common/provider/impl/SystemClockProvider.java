package com.spring.snapshot.common.provider.impl;

import com.spring.snapshot.common.provider.ClockProvider;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
class SystemClockProvider implements ClockProvider {

    @Override
    public Long millis() {
        return Clock.systemUTC().millis();
    }

}
