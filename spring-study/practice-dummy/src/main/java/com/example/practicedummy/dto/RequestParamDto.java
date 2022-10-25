package com.example.practicedummy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
//@NoArgsConstructor
//@Setter
@Getter
public class RequestParamDto {
    private int num;

    @NotBlank
    private String str;
}
