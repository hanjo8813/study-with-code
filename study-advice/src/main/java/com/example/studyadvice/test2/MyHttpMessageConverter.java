package com.example.studyadvice.test2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class MyHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public MyHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
        objectMapper.registerModule(new JavaTimeModule());
        setObjectMapper(objectMapper);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return canWrite(mediaType);
    }
}