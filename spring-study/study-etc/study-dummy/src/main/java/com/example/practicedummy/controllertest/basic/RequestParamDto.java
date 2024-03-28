package com.example.practicedummy.controllertest.basic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
