package com.spring.snapshot.common.provider;

public interface EncoderProvider {

    String encode(String data);

    boolean matches(String data, String encoded);

}
