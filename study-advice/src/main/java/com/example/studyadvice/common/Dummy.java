package com.example.studyadvice.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Dummy {

    private String str;
    private int num;
}
