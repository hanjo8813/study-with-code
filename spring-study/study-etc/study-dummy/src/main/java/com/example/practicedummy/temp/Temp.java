package com.example.practicedummy.temp;

import java.util.Collection;
import java.util.HashMap;

public class Temp {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");

        Collection<String> values = map.values();


        System.out.println(values);
        System.out.println(map);
    }
}
