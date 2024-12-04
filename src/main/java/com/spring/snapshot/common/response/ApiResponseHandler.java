package com.spring.snapshot.common.response;

import com.spring.snapshot.common.exception.ApplicationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

@ControllerAdvice
class ApiResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public ApiResponse<?> beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (body instanceof ApplicationException e) {
            response.setStatusCode(e.getStatus());
            return ApiResponse.from(e);
        }

        if (body instanceof ApiResponse<?> apiResponse) {
            response.setStatusCode(apiResponse.status());
            return apiResponse;
        }

        if (body instanceof ResponseEntity<?> responseEntity) {
            HttpStatus status = (HttpStatus) responseEntity.getStatusCode();
            response.setStatusCode(status);
            return ApiResponse.of(status, responseEntity.getBody());
        }

        HttpStatus status = extractResponseStatus(returnType).orElse(HttpStatus.OK);
        response.setStatusCode(status);
        return ApiResponse.of(status, body);
    }

    private Optional<HttpStatus> extractResponseStatus(MethodParameter returnType) {
        ResponseStatus responseStatus = returnType.getMethodAnnotation(ResponseStatus.class);
        return responseStatus == null ? Optional.empty() : Optional.of(responseStatus.value());
    }

}
