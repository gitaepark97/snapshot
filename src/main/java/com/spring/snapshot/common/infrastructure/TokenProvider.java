package com.spring.snapshot.common.infrastructure;

import java.time.Duration;
import java.util.Map;

public interface TokenProvider {

    String issue(Map<String, Object> claims, Duration expiration);

    Map<String, Object> parse(String token);

}
