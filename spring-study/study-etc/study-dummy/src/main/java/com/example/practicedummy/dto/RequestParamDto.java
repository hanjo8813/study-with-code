package com.example.practicedummy.dto;

import com.example.practicedummy.enums.DummyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
//@NoArgsConstructor
//@Setter
@Getter
public class RequestParamDto {
    @Positive
    private int num;

    @NotBlank
    private String str;

    @NotNull
    private DummyEnum dummyEnum;
}
