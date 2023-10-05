package com.example.studyadvice.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {

    private T data;

}
