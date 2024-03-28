package com.example.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Dummy {

    private int num;
    private String str;
}