package com.example.practicedummy.controller.a_시간형식;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
public class ResponseDto {

    private LocalDateTime pureDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime t;
}

// "pureDt": "2022-08-25T10:13:30.635601",
// "dt": "2022-08-25T01:41",
// "t": "01:41:21"
