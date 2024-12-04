package com.spring.snapshot.common.provider;

import java.util.Map;

public interface TokenProvider {

    String issue(Map<String, Object> claims);

    Map<String, Object> parse(String token);

}
