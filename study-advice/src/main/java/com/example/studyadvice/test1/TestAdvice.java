package com.example.studyadvice.test1;

import com.example.studyadvice.common.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class TestAdvice implements ResponseBodyAdvice<Object> {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response
    ) {
        if(!pathMatcher.match("/test/**", request.getURI().getPath())){
            return body;
        }
        if(body instanceof ApiResponse<?>) {
            return body;
        }
        return ApiResponse.of(body);
    }
}
